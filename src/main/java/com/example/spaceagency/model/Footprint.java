package com.example.spaceagency.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Footprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "footprint_id")
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn
    private Set<Coordinate> coordinates;

    public Long getId() {
        return id;
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }
}
