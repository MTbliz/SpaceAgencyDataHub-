package com.example.spaceagency.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    private Mission mission;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime acquisitionDate;

    private FootprintDTO footprint;

    private BigDecimal price;

    private FileDb fileDb;

    private String url;

    public ProductDTO(Mission mission, ZonedDateTime acquisitionDate, FootprintDTO footprint, BigDecimal price, FileDb fileDb, String url) {
        this.mission = mission;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.fileDb = fileDb;
        this.url = url;
    }

    public Mission getMission() {
        return mission;
    }

    public ZonedDateTime getAcquisitionDate() {
        return acquisitionDate;
    }

    public FootprintDTO getFootprint() {
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

    public Product transferProductDTOtoProduct() {

        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(this.getFootprint().getCoordinate1());
        coordinateList.add(this.getFootprint().getCoordinate2());
        coordinateList.add(this.getFootprint().getCoordinate3());
        coordinateList.add(this.getFootprint().getCoordinate4());

        Footprint footprint = new Footprint(this.getFootprint().getId(), coordinateList);

        Product product = new Product(
                this.getMission(),
                this.getAcquisitionDate(),
                footprint,
                this.getPrice(),
                this.getFileDb(),
                this.getUrl());
        return product;
    }
}
