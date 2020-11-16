package com.spatbee.gowhack.heuristics;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.spatbee.gowhack.GameBoard;
import com.spatbee.gowhack.Token;
import com.spatbee.gowhack.Turn;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;

public class GameBoardEvaluationWrapper {

    private GameBoard gameBoard;

    private Double numberOfPairs;
    private Double numberOfFiveMatchTurns;
    private Double numberOfFourMatchTurns;
    private Double score;
    private Map<Token, Double> tokenCountMap;
    private Map<Token, Double> tokenHeightMap;
    private List<Turn> turns;

    public GameBoardEvaluationWrapper(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Double getNumberOfPairs() {
        if (numberOfPairs == null) {
            numberOfPairs = 0d;
            numberOfPairs += getNumberOfPairsHorizontal();
            numberOfPairs += getNumberOfPairsVertical();
        }
        return numberOfPairs;
    }

    private Double getNumberOfPairsHorizontal() {
        numberOfPairs = 0d;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 7; col++) {
                if (gameBoard.getTokenGrid()[row][col] == gameBoard.getTokenGrid()[row][col + 1]) {
                    numberOfPairs++;
                }
            }
        }
        return numberOfPairs;
    }

    private Double getNumberOfPairsVertical() {
        numberOfPairs = 0d;
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 8; col++) {
                if (gameBoard.getTokenGrid()[row][col] == gameBoard.getTokenGrid()[row + 1][col]) {
                    numberOfPairs++;
                }
            }
        }
        return numberOfPairs;
    }

    public Double getNumberOfToken(Token token) {
        if (tokenCountMap == null) {
            tokenCountMap = new EnumMap<>(Token.class);
        }
        if (!tokenCountMap.containsKey(token)) {
            Double count = 0d;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (gameBoard.getTokenGrid()[row][col] == token) {
                        count++;
                    }
                }
            }
            tokenCountMap.put(token, count);
        }
        return tokenCountMap.get(token);
    }

    public Double getAverageHeightOfTokenType(Token token) {
        if (tokenHeightMap == null) {
            tokenHeightMap = new EnumMap<>(Token.class);
        }
        if (!tokenHeightMap.containsKey(token)) {
            Double count = 0d;
            Double totalHeight = 0d;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (gameBoard.getTokenGrid()[row][col] == token) {
                        count++;
                        totalHeight += 8 - row;
                    }
                }
            }
            tokenHeightMap.put(token, count == 0d ? 0 : (totalHeight / count));
        }
        return tokenHeightMap.get(token);
    }

    public Double getTurnsLeft() {
        return Double.valueOf(gameBoard.getTurnsLeft());
    }

    private void evaluateNumberOfFourAndFiveMatchTurns() {
        numberOfFourMatchTurns = 0d;
        numberOfFiveMatchTurns = 0d;
        for (Turn turn : turns) {
            try {
                int turnSize = gameBoard.getSizeOfTurnBeforeWithoutGravity(turn);
                if (turnSize == 4) {
                    numberOfFourMatchTurns++;
                } else if (gameBoard.getSizeOfTurnBeforeWithoutGravity(turn) > 4) {
                    numberOfFiveMatchTurns++;
                }
            } catch (MatchDoesNotContainSingleTurnCoordinateException e) {
                e.printStackTrace();
            }
        }
    }

    public Double getNumberOfFiveMatchTurns() {
        if (turns == null) {
            turns = gameBoard.getAllTurns();
        }
        if (numberOfFiveMatchTurns == null) {
            evaluateNumberOfFourAndFiveMatchTurns();
        }
        return numberOfFiveMatchTurns;
    }

    public Double getNumberOfFourMatchTurns() {
        if (turns == null) {
            turns = gameBoard.getAllTurns();
        }
        if (numberOfFourMatchTurns == null) {
            evaluateNumberOfFourAndFiveMatchTurns();
        }
        return numberOfFourMatchTurns;

    }

    public Double getNumberOfTurnsAvailable() {
        if(turns == null) {
            turns = gameBoard.getAllTurns();
        }
        return Double.valueOf(turns.size());
    }

    public Double getScore() {
        if(score == null) {
            score = Double.valueOf(gameBoard.score());
        }
        return score;
    }

}
