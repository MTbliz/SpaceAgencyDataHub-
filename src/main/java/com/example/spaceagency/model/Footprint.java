package com.example.spaceagency.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Footprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "footprint_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Coordinate> coordinates;

    public Footprint() {
    }

    public Footprint(Long id, List<Coordinate> coordinates) {
        this.id=id;
        this.coordinates=coordinates;
    }
    public Long getId() {
        return id;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}
