package com.example.spaceagency.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(generator = "APPUSER_ID_GENERATOR")
    @GenericGenerator(name = "APPUSER_ID_GENERATOR", strategy = "enhanced-sequence",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "appuser_sequence"))
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    //@NotNull
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id")
    //private Address address;
    private String address;

    public AppUser() {

    }

    public AppUser(@NotNull String firstName, @NotNull String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

    }

    public AppUser(Long id, @NotNull String firstName, @NotNull String lastName, @NotBlank @Size(max = 50) @Email String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id) &&
                firstName.equals(appUser.firstName) &&
                lastName.equals(appUser.lastName) &&
                address.equals(appUser.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
