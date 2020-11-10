package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;
import com.spatbee.gowhack.exception.ReadException;

public class App {
    public static void main(String[] args) throws AWTException, IOException, ReadException, IllegalBoardStateException,
            MatchDoesNotContainSingleTurnCoordinateException
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
        // System.out.println(gameBoard);
        for(int i = 0; i < 1; i++) {
            GameBoard gameBoardClone = gameBoard.deepClone();
            long start = System.currentTimeMillis();
            // GameCoordinator.completeGameWithRandomMoves(gameBoardClone);
            while(gameBoardClone.getTurnsLeft() > 0) {
                System.out.println("turns " + gameBoardClone.getTurnsLeft());
                gameBoardClone.doTurn(GameCoordinator.getBestTurnMonteCarlo(gameBoardClone));
            }
            System.out.println("turns " + gameBoardClone.getTurnsLeft());
            System.out.println("score: " + gameBoardClone.score());
            System.out.println(System.currentTimeMillis() - start + "ms");    
        }
    }
}
