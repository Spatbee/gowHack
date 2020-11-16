package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.CouldNotScrambleBoardException;
import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;
import com.spatbee.gowhack.heuristics.gene.RandomGene;

public class App {
    public static void main(String[] args) throws AWTException, IOException, IllegalBoardStateException,
            MatchDoesNotContainSingleTurnCoordinateException, CouldNotScrambleBoardException {
        
        // GamePlayer.playGame();
        int total = 0;
        for(int i = 0; i < 10; i++) {
            GameBoard gameBoard = NewBoardGenerator.generateStartingGameBoard();
            while(gameBoard.getTurnsLeft() > 0) {
                gameBoard.doTurn(GameCoordinator.getBestTurnFromHeuristicEvaluation(gameBoard, new RandomGene()));
            }
            int score = gameBoard.score();
            System.out.println(score);
            total+=score;
        }
        System.out.println("Average constant gene score: " + (total/10));
    }
}
