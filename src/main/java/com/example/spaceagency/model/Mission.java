package com.example.spaceagency.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ImageryType type;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime finishDate;

    @OneToMany(mappedBy = "mission")
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageryType getType() {
        return type;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getFinishDate() {
        return finishDate;
    }

}
