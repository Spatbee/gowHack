package com.spatbee.gowhack;

public class Turn {

    private Coordinate coordinate1, coordinate2;
    
    public Turn(Coordinate coordinate1, Coordinate coordinate2) {
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
    }

    public Coordinate getCoordinate1() {
        return coordinate1;
    }

    public void setCoordinate1(Coordinate coordinate1) {
        this.coordinate1 = coordinate1;
    }

    public Coordinate getCoordinate2() {
        return coordinate2;
    }

    public void setCoordinate2(Coordinate coordinate2) {
        this.coordinate2 = coordinate2;
    }


}
