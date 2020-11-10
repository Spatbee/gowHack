package com.spatbee.gowhack;

import java.util.List;
import java.util.stream.Collectors;

public class Match {
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    
    List<Coordinate> coordinates;
    
    public Match(int type, Coordinate start, Coordinate end) {
        if(type == VERTICAL) {
            if(start.getRow() != end.getRow()) {
                throw new IllegalArgumentException();
            }
            for(int col = start.getCol(); col < end.getCol(); col++) {
                coordinates.add(new Coordinate(start.getRow(), col));
            }
        } else {
            if(start.getCol() != end.getCol()) {
                throw new IllegalArgumentException();
            }
            for(int row = start.getRow(); row < end.getRow(); row++) {
                coordinates.add(new Coordinate(row, start.getCol()));
            }
        }
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
    
    public boolean intersects(Match other) {
        return ! coordinates.stream().filter(other.getCoordinates()::contains).collect(Collectors.toList()).isEmpty();
    }

    public void addMatch(Match other) {
        for(Coordinate coordinate : other.getCoordinates()) {
            if(!coordinates.contains(coordinate)) {
                coordinates.add(coordinate);
            }
        }
    }
}