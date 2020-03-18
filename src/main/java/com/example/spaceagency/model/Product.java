package com.example.spaceagency.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    private LocalDateTime acquisitionDAte;

    private Footprint footprint;

    private BigDecimal price;

    @JsonIgnore
    private String url;

    public Long getId() {
        return id;
    }

    public Mission getMission() {
        return mission;
    }

    public LocalDateTime getAcquisitionDAte() {
        return acquisitionDAte;
    }

    public Footprint getFootprint() {
        return footprint;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

}
