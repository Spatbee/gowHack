package com.spatbee.gowhack;

import java.util.List;

import com.spatbee.gowhack.exception.CouldNotScrambleBoardException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;

public class GameCoordinator {

    public static int completeGameWithRandomMoves(GameBoard gameBoard) {
        while(gameBoard.getTurnsLeft() > 0) {
            List<Turn> turns = gameBoard.getAllTurns();
            if(turns.size() == 0) {
                System.out.println("ran out of turns.... I need to account for this");
                return 0;
            }
            Turn turn = turns.get(RandomUtil.randomInt(turns.size()));
            try {
                gameBoard.doTurn(turn);
            } catch (MatchDoesNotContainSingleTurnCoordinateException | CouldNotScrambleBoardException e) {
                System.out.println("The match might have been illegal.");
                System.out.println("row1: " + turn.getCoordinate1().getRow() + " col1: " + turn.getCoordinate1().getCol() + " row2: " + turn.getCoordinate2().getRow() + " col2: " + turn.getCoordinate2().getCol());
                return 0;
            }
            
        }
        return gameBoard.score();
    }

    public static Turn getBestTurnMonteCarlo(GameBoard gameBoard) {
        List<Turn> turns = gameBoard.getAllTurns();
        if(turns.size() == 0) {
            System.out.println("ran out of turns.... I need to account for this");
            return null;
        }
        Turn bestTurn = turns.get(0);
        long bestTotalScore = 0;
        for(Turn turn : turns) {
            long totalScore = 0;
            for(int i = 0; i < 100; i ++) {
                GameBoard gameBoardClone = gameBoard.deepClone();
                try {
                    gameBoardClone.doTurn(turn);
                } catch (MatchDoesNotContainSingleTurnCoordinateException | CouldNotScrambleBoardException e) {
                    System.out.println("The match might have been illegal.");
                    System.out.println("row1: " + turn.getCoordinate1().getRow() + " col1: " + turn.getCoordinate1().getCol() + " row2: " + turn.getCoordinate2().getRow() + " col2: " + turn.getCoordinate2().getCol());
                    return null;
                }
                totalScore += completeGameWithRandomMoves(gameBoardClone);
            }
            if(totalScore > bestTotalScore) {
                bestTurn = turn;
                bestTotalScore = totalScore;
            }
            // System.out.println("turn score: " + (totalScore /100));
        }
        return bestTurn;
    }
    
}
