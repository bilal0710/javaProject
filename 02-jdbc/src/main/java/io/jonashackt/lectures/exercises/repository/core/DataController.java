package io.jonashackt.lectures.exercises.repository.core;

import io.jonashackt.lectures.exercises.model.Address;
import io.jonashackt.lectures.exercises.model.AddressBook;
import io.jonashackt.lectures.exercises.model.Person;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataController {

    private static final String PERSISTENCE_UNIT_NAME ="addressbook-unit";
    private  EntityManagerFactory entityManagerFactory;
    private static DataController instance;

    public static DataController getInstance()
    {
        if( instance == null )
            instance = new DataController();
        return instance;
    }

    public DataController()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
    }


    public IGenericDao<Person> getPersonDao()
    {
        return new JpaGenericDao<Person, Long>( Person.class,this.entityManagerFactory.createEntityManager() );
    }

    public IGenericDao<Address>getAddressDao()
    {
        return new JpaGenericDao<Address, Long>( Address.class, this.entityManagerFactory.createEntityManager() );
    }
}

