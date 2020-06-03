package io.jonashackt.lectures.exercises.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Person extends AbstractDatabaseEntity implements Comparable<Person>, Serializable
{
    private  String firstName;
    private  String lastName;
    private  String eMail;



    public Person(String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    public Person() {
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
