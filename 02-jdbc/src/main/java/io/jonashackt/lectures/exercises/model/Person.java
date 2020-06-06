package io.jonashackt.lectures.exercises.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Person extends AbstractDatabaseEntity implements Comparable<Person>, Serializable
{
    private  String firstName;
    private  String lastName;
    private  String eMail;

    @ManyToOne(cascade= CascadeType.PERSIST)
    private AddressBook addressBook;



    public Person(String firstName, String lastName, String eMail,AddressBook addressBook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.addressBook = addressBook;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int compareTo(Person person) {
        return this.lastName.compareTo(person.getLastName());
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " (" + this.eMail + ")";
    }
}
