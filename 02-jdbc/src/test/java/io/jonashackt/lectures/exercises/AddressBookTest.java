package io.jonashackt.lectures.exercises;

import io.jonashackt.lectures.exercises.model.AddressBook;
import io.jonashackt.lectures.exercises.model.Person;
import io.jonashackt.lectures.exercises.model.excptions.PersonNotFoundException;
import io.jonashackt.lectures.exercises.repository.exception.JpaStorageController;
import io.jonashackt.lectures.exercises.repository.exception.StorageException;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressBookTest {

    AddressBook addressBook;
    AddressBook ab;
    Person maxMustermann;
    Person emmaWeber;
    Person manfredHerold;
    ResultSet rs;
    Statement stmt;
    Connection conn;
    JpaStorageController controller;


   /* @BeforeEach
    public void setAll() {
        conn = null;
        try {
            /* make a Connection with H2 Database Memory*/
           /* Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:mem:AddressBook");*/

            /*
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");

            stmt = conn.createStatement();

            stmt.executeUpdate("DROP TABLE IF EXISTS AddressBook");
            stmt.execute("CREATE Table AddressBook (addressBookID INT PRIMARY KEY NOT NULL)");

            stmt.execute("INSERT INTO AddressBook VALUES( 1 )");


            stmt.execute("CREATE Table Address (addressID INT PRIMARY KEY NOT NULL," +
                    "                             street VARCHAR (255) NOT NULL," +
                    "                             city VARCHAR (255) NOT NULL," +
                    "                             zipCode VARCHAR (255) NOT NULL)");



            stmt.execute("INSERT INTO Address VALUES( 1,'Grünstraße','Erfurt','12345' )");
            stmt.execute("INSERT INTO Address VALUES( 2,'Dr.Kölz','Suhl','54321' )");

            stmt.execute("CREATE Table Person (personID INT PRIMARY KEY NOT NULL ," +
                    "                             firstName VARCHAR (255) NOT NULL," +
                    "                             lastName VARCHAR (255) NOT NULL," +
                    "                             EMAIL VARCHAR(50) NOT NULL, " +
                    "                             addressBookID INT NOT NULL," +
                    "                             addressID INT NOT NULL," +
                    "                             FOREIGN KEY (addressBookID) REFERENCES AddressBook (addressBookID)," +
                    "                             FOREIGN KEY (addressID) REFERENCES Address (addressID))");


            stmt.execute("INSERT INTO Person VALUES( 1, 'Max' ,'Mustermann','max.mustermann@email.de',1 ,1)");
            stmt.execute("INSERT INTO Person VALUES( 2, 'Emma' ,'Weber','emma.mueller@post.de',1,1)");
            stmt.execute("INSERT INTO Person VALUES( 3, 'Manfred' ,'Herold','manfred@herold.de',1 ,2)");


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void should_give_the_firstname_of_the_Person() throws SQLException {
        rs = stmt.executeQuery("SELECT firstname from Person ");
        while (rs.next())
            System.out.println(rs.getString("firstname"));

    }*/


    /*@Test
    public void should_give_the_City_of_the_Person() throws SQLException {
        rs = stmt.executeQuery("SELECT city from Person p join Address a on p.addressID=a.addressID");
        while (rs.next())
            System.out.println(rs.getString("city"));

    }



    @After
    public void should_close_all_Programm(){

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}*/


    @BeforeEach
    void prepareAddressBook()  {
        addressBook = new AddressBook();
        ab = new AddressBook();

        maxMustermann   = new Person("Max", "Mustermann", "max.mustermann@email.de");
        emmaWeber       = new Person("Emma", "Weber", "emma.mueller@post.de");
        manfredHerold   = new Person("Manfred", "Herold", "manfred@herold.de");
        controller= new  JpaStorageController();


        addressBook.addContact(maxMustermann);
        addressBook.addContact(emmaWeber);
        addressBook.addContact(manfredHerold);

    }

        @Test
        void controller_should_add_a_Data_line() throws StorageException
        {

            controller.saveAddressbook(addressBook);
            ab = controller.loadAddressbook();

                assertEquals(3,ab.getSize());

        }




    @Test
    void addressbook_should_store_persons_with_name_surename_email() throws PersonNotFoundException {

        assertEquals(maxMustermann, addressBook.getContactByLastName("Mustermann"));
        assertEquals(emmaWeber, addressBook.getContactByLastName("Weber"));
        assertEquals(manfredHerold, addressBook.getContactByLastName("Herold"));
    }

    @Test
    void should_throw_person_not_found_exception_with_unknown_person() {

        assertThrows(PersonNotFoundException.class, () -> {
            addressBook.getContactByLastName("Gates");
        });
    }

    @Test
    void persons_should_be_sorted_ascending_according_to_lastname() {

        assertEquals(3, addressBook.getSize(), "After adding 3 persons, the address book should contain 3 persons.");

        assertEquals(manfredHerold, addressBook.getPersonByIndex(0));

    }

    @Test
    void addressBook_should_have_overview_on_how_often_lastnames_occur_in_it() {
        assertEquals(1, addressBook.getFrequencyOfLastName("Mustermann"));

        addressBook.addContact(new Person("Moritz", "Mustermann", "foo@bar.com"));
        addressBook.addContact(new Person("Tobias", "Mustermann", "foo@bar.com"));
        addressBook.addContact(new Person("Matze", "Mustermann", "foo@bar.com"));

        assertEquals(4, addressBook.getFrequencyOfLastName("Mustermann"));
    }

    @Test
    void person_should_have_a_human_readable_toString_output() {
        Person felixMeyer = new Person("Felix", "Meyer", "felix@meyer.io");

        assertEquals("Felix Meyer (felix@meyer.io)", felixMeyer.toString());
    }
}

