package com.example.spaceagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
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

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private FileDb fileDb;

    public Mission(){};

    public Mission(String name, ImageryType type, ZonedDateTime startDate, ZonedDateTime finishDate, FileDb fileDb) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.fileDb = fileDb;
    }

    public Mission(Long id, String name, ImageryType type, ZonedDateTime startDate, ZonedDateTime finishDate, FileDb fileDb) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.fileDb = fileDb;
    }

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

    public FileDb getFileDb() {
        return fileDb;
    }
}
