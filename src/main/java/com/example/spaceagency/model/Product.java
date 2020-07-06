package com.example.spaceagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime acquisitionDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "footprint_id", referencedColumnName = "footprint_id")
    private Footprint footprint;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private FileDb fileDb;

    @JsonIgnore
    private String url;

    public Product() {
    }

    public Product(Mission mission, ZonedDateTime acquisitionDate, Footprint footprint, BigDecimal price, FileDb fileDb, String url) {
        this.mission = mission;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.fileDb = fileDb;
        this.url = url;
    }

    public Product(Mission mission, ZonedDateTime acquisitionDate, Footprint footprint, BigDecimal price) {
        this.mission = mission;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Mission getMission() {
        return mission;
    }

    public ZonedDateTime getAcquisitionDate() {
        return acquisitionDate;
    }

    public Footprint getFootprint() {
        return footprint;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FileDb getFileDb() {
        return fileDb;
    }

    public String getUrl() {
        return url;
    }
}
