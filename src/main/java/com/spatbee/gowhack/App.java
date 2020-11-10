package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.CouldNotScrambleBoardException;
import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;
import com.spatbee.gowhack.exception.ReadException;

public class App {
    public static void main(String[] args) throws AWTException, IOException, ReadException, IllegalBoardStateException,
            MatchDoesNotContainSingleTurnCoordinateException, CouldNotScrambleBoardException
    {
        Token[][] tokenGrid = {
            {Token.BAG, Token.SILVER, Token.BAG, Token.SILVER, Token.COPPER, Token.BAG, Token.SILVER, Token.SILVER},
            {Token.GOLD, Token.COPPER, Token.SILVER, Token.COPPER, Token.COPPER, Token.SILVER, Token.GOLD, Token.COPPER},
            {Token.GOLD, Token.SILVER, Token.GOLD, Token.COPPER, Token.SILVER, Token.BAG, Token.SILVER, Token.SILVER},
            {Token.BAG, Token.GOLD, Token.SILVER, Token.BAG, Token.BAG, Token.SILVER, Token.SILVER, Token.BAG},
            {Token.COPPER, Token.SILVER, Token.SILVER, Token.GOLD, Token.SILVER, Token.COPPER, Token.BAG, Token.SILVER},
            {Token.BAG, Token.COPPER, Token.GOLD, Token.GOLD, Token.COPPER, Token.GOLD, Token.SILVER, Token.BAG},
            {Token.GOLD, Token.SILVER, Token.SILVER, Token.COPPER, Token.SILVER, Token.BAG, Token.BAG, Token.COPPER},
            {Token.COPPER, Token.BAG, Token.BAG, Token.COPPER, Token.COPPER, Token.BAG, Token.SILVER, Token.BAG}
        };
        GameBoard gameBoard = new GameBoard(12, tokenGrid);
        long totalScore = 0;
        for(int i = 0; i < 5; i++) {
            GameBoard gameBoardClone = gameBoard.deepClone();
            long start = System.currentTimeMillis();
            while(gameBoardClone.getTurnsLeft() > 0) {
                gameBoardClone.doTurn(GameCoordinator.getBestTurnMonteCarlo(gameBoardClone));
            }
            System.out.println("round " + i + " completed in " + (System.currentTimeMillis() - start) + "ms");
            totalScore += gameBoardClone.score();  
        }
        System.out.println("average score: " + (totalScore / 5));
    }
}
