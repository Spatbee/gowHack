package com.spatbee.gowhack;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;

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

    public Coordinate getCoordinateInTurn(Turn turn) throws MatchDoesNotContainSingleTurnCoordinateException {
        List<Coordinate> matchingCoordinates = coordinates.stream().filter(coordinate -> {
            return coordinate.equals(turn.getCoordinate1()) || coordinate.equals(turn.getCoordinate2());
        }).collect(Collectors.toList());
        if(matchingCoordinates.size() != 1) {
            throw new MatchDoesNotContainSingleTurnCoordinateException();
        }
        return matchingCoordinates.get(0);
    }

    public Coordinate getRandomCoordinate() {
        return coordinates.get(RandomUtil.randomInt(coordinates.size()));
    }
}