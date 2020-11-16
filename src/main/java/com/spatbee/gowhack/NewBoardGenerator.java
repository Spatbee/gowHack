package com.spatbee.gowhack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.spatbee.gowhack.exception.IllegalBoardStateException;

public class NewBoardGenerator {

    private NewBoardGenerator() {
        // private constructor to hide implicit public one
    }

    public static GameBoard generateStartingGameBoard() throws IllegalBoardStateException {
        Token[][] tokenGrid = new Token[8][8];
        for(int row = 0; row < 8; row ++) {
            for(int col = 0; col < 8; col++) {
                List<Token> tokens = Arrays.asList(Token.COPPER, Token.SILVER, Token.GOLD, Token.BAG);
                Collections.shuffle(tokens);
                int index = 0;
                while(
                    !(row < 2 || tokenGrid[row - 2][col] != tokens.get(index) || tokenGrid[row - 1][col] != tokens.get(index)) ||
                    !(col < 2 || tokenGrid[row][col - 2] != tokens.get(index) || tokenGrid[row][col - 1] != tokens.get(index))
                ) {
                    index++;
                }
                tokenGrid[row][col] = tokens.get(index);
            }
        }
        return new GameBoard(12, tokenGrid);
    }
}
