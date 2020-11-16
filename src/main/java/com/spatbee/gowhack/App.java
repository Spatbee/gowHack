package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.CouldNotScrambleBoardException;
import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;

public class App {
    public static void main(String[] args) throws AWTException, IOException, IllegalBoardStateException,
            MatchDoesNotContainSingleTurnCoordinateException, CouldNotScrambleBoardException {
        // Token[][] tokenGrid = {
        //     {Token.BAG, Token.SILVER, Token.BAG, Token.SILVER, Token.COPPER, Token.BAG, Token.SILVER, Token.SILVER},
        //     {Token.GOLD, Token.COPPER, Token.SILVER, Token.COPPER, Token.COPPER, Token.SILVER, Token.GOLD, Token.COPPER},
        //     {Token.GOLD, Token.SILVER, Token.GOLD, Token.COPPER, Token.SILVER, Token.BAG, Token.SILVER, Token.SILVER},
        //     {Token.BAG, Token.GOLD, Token.SILVER, Token.BAG, Token.BAG, Token.SILVER, Token.SILVER, Token.BAG},
        //     {Token.COPPER, Token.SILVER, Token.SILVER, Token.GOLD, Token.SILVER, Token.COPPER, Token.BAG, Token.SILVER},
        //     {Token.BAG, Token.COPPER, Token.GOLD, Token.GOLD, Token.COPPER, Token.GOLD, Token.SILVER, Token.BAG},
        //     {Token.GOLD, Token.SILVER, Token.SILVER, Token.COPPER, Token.SILVER, Token.BAG, Token.BAG, Token.COPPER},
        //     {Token.COPPER, Token.BAG, Token.BAG, Token.COPPER, Token.COPPER, Token.BAG, Token.SILVER, Token.BAG}
        // };
        // GameBoard gameBoard = new GameBoard(1, tokenGrid);
        // long totalScoreMonteCarloAverage = 0;
        // long totalScoreMonteCarloMedian = 0;
        // for(int i = 0; i < 20; i++) {
        //     GameBoard gameBoardClone = gameBoard.deepClone();
        //     while(gameBoardClone.getTurnsLeft() > 0) {
        //         gameBoardClone.doTurn(GameCoordinator.getBestTurnMonteCarlo(gameBoardClone));
        //     }
        //     totalScoreMonteCarloAverage += gameBoardClone.score();
        //     gameBoardClone = gameBoard.deepClone();
        //     while(gameBoardClone.getTurnsLeft() > 0) {
        //         gameBoardClone.doTurn(GameCoordinator.getBestMedianTurnMonteCarlo(gameBoardClone));
        //     }
        //     totalScoreMonteCarloMedian += gameBoardClone.score();
        //     System.out.println("completed round " + i);
        // }
        // System.out.println("Average: " + (totalScoreMonteCarloAverage/20));
        // System.out.println("Median: " + (totalScoreMonteCarloMedian/20));
        GamePlayer.playGame();
    }
}
