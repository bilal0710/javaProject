package io.jonashackt.lectures.exercises.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address extends AbstractDatabaseEntity{


        private String street;
        private String city;
        private String zipCode;

    /*public Address(String street, String city, String zipCode) {
        super();
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }*/

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
