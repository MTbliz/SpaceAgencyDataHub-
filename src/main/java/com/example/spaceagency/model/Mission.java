package com.example.spaceagency.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

}
