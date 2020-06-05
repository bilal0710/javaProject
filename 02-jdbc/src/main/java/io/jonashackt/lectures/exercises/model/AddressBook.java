package io.jonashackt.lectures.exercises.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jonashackt.lectures.exercises.model.excptions.*;

import javax.persistence.CascadeType;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class AddressBook extends AbstractDatabaseEntity{
    private static final Logger LOG = LoggerFactory.getLogger(AddressBook.class);

    @OneToMany( cascade= CascadeType.PERSIST )
    private List<Person> persons=new ArrayList<>() ;

    public AddressBook() {

    }

    public AddressBook(ArrayList<Person> Contact) {
        persons= Contact;
    }


    public List<Person> getPersons() {

        return  persons;
    }

    public void addContact(Person person) {
        LOG.debug("Adding Person to AddressBook: " + person.toString());
        this.persons.add(person);
        Collections.sort(persons);
    }

    public Person getContactByLastName(String lastName) throws PersonNotFoundException {
        for(Person person : persons) {
            if(person.getLastName().equals(lastName)) {
                return person;
            }
        }
        String errorMessage = "Person with the last name '" + lastName + "' could not be found!";
        LOG.error(errorMessage);
        throw new PersonNotFoundException(errorMessage);
    }

    public int getSize() {
        LOG.info("There are currently " + persons.size() + " persons in the address book.");
        return persons.size();
    }

    public Person getPersonByIndex(int index) {
        Person person = persons.get(index);
        LOG.info("Person " + person.toString() + " is currently stored at index number " + index);
        return person;
    }

    public long getFrequencyOfLastName(String lastName) {
        LOG.debug("Let's find out, how many persons with last name " + lastName + " we have in our address book right now!");
        return persons.stream().map(Person::getLastName).filter(lastName::equals).count();

    }
}
