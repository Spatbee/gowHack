package com.spatbee.gowhack;

public class GameBoard {

    private int turns;
    private Token[][] tokenGrid;

    public GameBoard(int turns, Token[][] tokenGrid) {
        this.turns = turns;
        this.tokenGrid = tokenGrid;
    }

    public String toString() {
        String s = "GameBoard:[\nturns: "+turns + "\n";
        for(int row = 0; row < tokenGrid.length; row ++) {
            for(int col = 0; col < tokenGrid[row].length; col ++) {
                s += tokenGrid[row][col].name();
                if(col != tokenGrid[row].length -1) {
                    s+=" ";
                }
            }
            s += "\n";
        }
        s += "]";
        return s;
    }
    
}
