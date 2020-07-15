package com.example.spaceagency.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class ProductWithoutFile {

    private Long id;

    private Mission mission;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime acquisitionDate;

    private Footprint footprint;

    private BigDecimal price;

    public ProductWithoutFile(Long id, Mission mission, ZonedDateTime acquisitionDate, Footprint footprint, BigDecimal price) {
        this.id = id;
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
}
