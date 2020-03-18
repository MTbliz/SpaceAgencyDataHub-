package com.example.spaceagency.model;

import java.io.Serializable;

public class Footprint implements Serializable {
Coordinate coordinate1;
Coordinate coordinate2;
Coordinate coordinate3;
Coordinate coordinate4;

    public Footprint(Coordinate coordinate1, Coordinate coordinate2, Coordinate coordinate3, Coordinate coordinate4) {
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.coordinate3 = coordinate3;
        this.coordinate4 = coordinate4;
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
