package io.jonashackt.lectures.exercises.repository.core;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class JpaGenericDao<T, ID extends Serializable> implements IGenericDao<T>
{
    private final Class<T> persistentClass;
    private EntityManager entityManager;

    public Class<T> getPersistentClass() {
        return persistentClass;
    }


    public JpaGenericDao(Class<T> type, EntityManager em )
    {
        this.persistentClass = type;
        this.entityManager = em;
    }

    public T findById( final ID id )
    {
        final T result = getEntityManager().find( persistentClass, id );
        return result;
    }

    /*public List<T> findAll()
    {
        Query query = getEntityManager().createNativeQuery( "SELECT * FROM " + getEntityClass().getSimpleName() );
        return  query.getResultList();
    }*/

    public Collection<T> findAll()
    {
        Query query = getEntityManager().createQuery( "SELECT e FROM " + getEntityClass().getCanonicalName() + " e" );
        return (Collection<T>) query.getResultList();
    }


    public void create(T entity )
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();
        //return entity;
    }

    
    public void update( T entity )
    {
        getEntityManager().getTransaction().begin();
        final T savedEntity = getEntityManager().merge( entity );
        getEntityManager().getTransaction().commit();
        //return savedEntity;
    }

    public void delete( ID id )
    {
        T entity = this.findById( id );
        this.delete( entity );
    }
    public void delete( T entity )
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove( entity );
        getEntityManager().getTransaction().commit();
    }
    public void delete( List<T> entries )
    {
        getEntityManager().getTransaction().begin();
        for( T entry : entries )
        {
            getEntityManager().remove( entry );
        }
        getEntityManager().getTransaction().commit();
    }
    public Class<T> getEntityClass()
    {
        return persistentClass;
    }

    public void setEntityManager( final EntityManager entityManager )
    {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }




}
