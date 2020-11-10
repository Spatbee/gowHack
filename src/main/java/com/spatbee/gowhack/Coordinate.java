package com.spatbee.gowhack;

public class Coordinate {
    private int row, col;
    
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if(! (other instanceof Coordinate)) {
            return false;
        }
        Coordinate c = (Coordinate) other;
        return row == c.row && col == c.col;
    }

}
