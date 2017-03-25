package pl.io4.model;

/**
 * Created by Zax37 on 22.03.2017.
 */

public class Employee {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String hashSHA1; //Pobierany z bazy danych i cache'owany.
    private String hashMD5; //Ustawiany dopiero po zalogowaniu.

    Employee(int id, String firstName, String lastName, String hashSHA1){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashSHA1 = hashSHA1;
    }
}