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
                    return null;
                }
                totalScore += completeGameWithRandomMoves(gameBoardClone);
            }
            if(totalScore > bestTotalScore) {
                bestTurn = turn;
                bestTotalScore = totalScore;
            }
        }
        return bestTurn;
    }
    
}
