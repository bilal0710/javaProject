package io.jonashackt.lectures.exercises.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass

public abstract class AbstractDatabaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    @Version
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date modified;


    public Long getId() {
        return id;
    }



    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @PrePersist
   void onCreate()
    {
        this.setCreated( new Date() );
    }
    @PreUpdate
    void onUpdate()
    {
        this.setModified( new Date() );
    }
}