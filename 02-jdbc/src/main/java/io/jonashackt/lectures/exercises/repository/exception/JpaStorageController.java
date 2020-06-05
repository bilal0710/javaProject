package io.jonashackt.lectures.exercises.repository.exception;

import io.jonashackt.lectures.exercises.model.AddressBook;
import io.jonashackt.lectures.exercises.model.Person;
import io.jonashackt.lectures.exercises.repository.core.DataController;
import io.jonashackt.lectures.exercises.repository.core.IGenericDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JpaStorageController implements IStorageController{

    public AddressBook loadAddressbook() throws StorageException
    {
        IGenericDao<Person> personDao = DataController.getInstance().getPersonDao();
        Collection<Person> personsFromDatabase = personDao.findAll();
        return new AddressBook( new ArrayList<Person>( personsFromDatabase ) );
    }

    public void deletePersonFromAddressbook( Person person) throws StorageException
    {
        IGenericDao<Person> personDao = DataController.getInstance().getPersonDao();
        Person personsFromDatabase = personDao.findById(person.getId());
        if(personsFromDatabase != null)
        {
            personDao.delete(person.getId());
        }
        else{
            System.out.println("the Person is not in the Databank");
        }
    }

    public Person editPersonFromAddressbook( Person person,String firstName) throws StorageException
    {
        Person p = new Person();
        IGenericDao<Person> personDao = DataController.getInstance().getPersonDao();
        Person personsFromDatabase = personDao.findById(person.getId());

        if(personsFromDatabase != null)
        {
            personsFromDatabase.setFirstName(firstName);
            p = personDao.update(personsFromDatabase);
        }
        else{
            System.out.println("the Person is not in the Databank");
        }
        return p;
    }


    public void deleteTableFromAddressbook(List<Person> persons) throws StorageException
    {
        IGenericDao<Person> personDao = DataController.getInstance().getPersonDao();
         personDao.delete(persons);

    }


   public void saveAddressbook( AddressBook addressbook ) throws StorageException
    {
        IGenericDao<Person> personDao = DataController.getInstance().getPersonDao();

          for( Person person : addressbook.getPersons() )
        {
            if( person.getId() == null )
                personDao.create( person );
            else
            personDao.update( person );


    }
    }
}

