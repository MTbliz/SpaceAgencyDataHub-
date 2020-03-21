package com.example.spaceagency.model;

import javax.persistence.*;

@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinate_id")
    private Long id;

    private double latitude;

    private double longitude;

    public Coordinate() {
    }

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
