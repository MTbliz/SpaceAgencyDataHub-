package com.example.spaceagency.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
