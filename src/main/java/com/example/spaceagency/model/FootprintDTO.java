package com.example.spaceagency.model;

public class FootprintDTO {

    private Long id;

    private Coordinate coordinate1;

    private Coordinate coordinate2;

    private Coordinate coordinate3;

    private Coordinate coordinate4;

    public FootprintDTO() {
    }

    public FootprintDTO(Coordinate coordinate1, Coordinate coordinate2, Coordinate coordinate3, Coordinate coordinate4) {
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.coordinate3 = coordinate3;
        this.coordinate4 = coordinate4;
    }

    public Long getId() {
        return id;
    }

    public Coordinate getCoordinate1() {
        return coordinate1;
    }

    public Coordinate getCoordinate2() {
        return coordinate2;
    }

    public Coordinate getCoordinate3() {
        return coordinate3;
    }

    public Coordinate getCoordinate4() {
        return coordinate4;
    }
}
