package com.example.spaceagency.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    private Long id;

    private Mission mission;

    private ZonedDateTime acquisitionDAte;

    private FootprintDTO footprint;

    private BigDecimal price;

    private String url;

    public ProductDTO(Mission mission, ZonedDateTime acquisitionDAte, FootprintDTO footprint, BigDecimal price, String url) {
        this.mission = mission;
        this.acquisitionDAte = acquisitionDAte;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public Mission getMission() {
        return mission;
    }

    public ZonedDateTime getAcquisitionDAte() {
        return acquisitionDAte;
    }

    public FootprintDTO getFootprint() {
        return footprint;
    }

    public BigDecimal getPrice() {
        return price;
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
                this.getAcquisitionDAte(),
                footprint,
                this.getPrice(),
                this.getUrl());
        return product;
    }
}
