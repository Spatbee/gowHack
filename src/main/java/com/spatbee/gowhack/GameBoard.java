package com.spatbee.gowhack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spatbee.gowhack.exception.IllegalBoardStateException;

public class GameBoard {

    private int turns;
    private Token[][] tokenGrid;

    public GameBoard(int turns, Token[][] tokenGrid) throws IllegalBoardStateException {
        this.turns = turns;
        this.tokenGrid = tokenGrid;
        if(matchExists()) {
            throw new IllegalBoardStateException();
        }
    }

    private boolean matchExists() {
        for(int row = 0; row < 6; row++) {
            for(int col = 0; col < 6; col++) {
                Token token = tokenGrid[row][col];
                if(token == tokenGrid[row][col+1] && token == tokenGrid[row][col+2] || token == tokenGrid[row+1][col] && token == tokenGrid[row+2][col]) {
                    return true;
                }
            }
        }
        return false;
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

    public GameBoard deepClone() {
        Token[][] tokenGridClone = new Token[8][8];
        for(int row = 0; row < 8; row ++) {
            tokenGridClone[row] = Arrays.copyOf(tokenGrid[row], 8);
        }
        try {
            return new GameBoard(turns, tokenGridClone);
        } catch (IllegalBoardStateException e){
            throw new IllegalArgumentException(); //this should never be thrown, since it's impossible to create a board with an illegal state.
        }
        
    }

    private boolean isLegal(Turn turn) {
        Token temp = tokenGrid[turn.getRow1()][turn.getCol1()];
        tokenGrid[turn.getRow1()][turn.getCol1()] = tokenGrid[turn.getRow2()][turn.getCol2()];
        tokenGrid[turn.getRow2()][turn.getCol2()] = temp;
        boolean isLegal = matchExists();
        tokenGrid[turn.getRow2()][turn.getCol2()] = tokenGrid[turn.getRow1()][turn.getCol1()];
        tokenGrid[turn.getRow1()][turn.getCol1()] = temp;
        return isLegal;
    }

    public List<Turn> getAllTurns() {
        List<Turn> legalTurns = new ArrayList<>();
        //vertical turns
        for(int row = 0; row < 7; row ++) {
            for(int col = 0; col < 8; col ++) {
                Turn turn = new Turn(row, col, row+1, col);
                if(isLegal(turn)) {
                    legalTurns.add(turn);
                }
            }
        }
        //horizontal turns
        for(int row = 0; row < 8; row ++) {
            for(int col = 0; col < 7; col ++) {
                Turn turn = new Turn(row, col, row, col+1);
                if(isLegal(turn)) {
                    legalTurns.add(turn);
                }
            }
        }
        return legalTurns;
    }

    public void doTurn(Turn turn) {
        boolean matchedFour = false;
        boolean matchedFive = false;
        //swap
        Token temp = tokenGrid[turn.getRow1()][turn.getCol1()];
        tokenGrid[turn.getRow1()][turn.getCol1()] = tokenGrid[turn.getRow2()][turn.getCol2()];
        tokenGrid[turn.getRow2()][turn.getCol2()] = temp;
        //combine matches involved in swap upgrading to spots in the turn
        //while there are matches
        //combine all matches choosing a random position for upgrade
        //elihw
        //gravity
        
        //change turn counter
        if(matchedFive) {
            turns++;
        } else if(!matchedFour) {
            turns--;
        }
    }

    private void combineMatches(Turn turn) {
        //priotize the two pieces swapped in the turn if the turn is not null
        
    }
    
}
