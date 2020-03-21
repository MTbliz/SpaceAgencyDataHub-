package com.example.spaceagency.model;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String firstName;

    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
