package com.spatbee.gowhack;

import java.util.List;

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
            } catch (MatchDoesNotContainSingleTurnCoordinateException e) {
                System.out.println("The match might have been illegal.");
                System.out.println("row1: " + turn.getCoordinate1().getRow() + " col1: " + turn.getCoordinate1().getCol() + " row2: " + turn.getCoordinate2().getRow() + " col2: " + turn.getCoordinate2().getCol());
                return 0;
            }
            
        }
        return gameBoard.score();
    }
    
}
