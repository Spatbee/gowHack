package com.spatbee.gowhack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;

public class GameBoard {

    private int turns;
    private Token[][] tokenGrid;

    public GameBoard(int turns, Token[][] tokenGrid) throws IllegalBoardStateException {
        this.turns = turns;
        this.tokenGrid = tokenGrid;
        if (matchExists()) {
            throw new IllegalBoardStateException();
        }
    }

    private boolean matchExists() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Token token = tokenGrid[row][col];
                if (token != null && token != Token.SAFE && (token == tokenGrid[row][col + 1] && token == tokenGrid[row][col + 2]
                        || token == tokenGrid[row + 1][col] && token == tokenGrid[row + 2][col])) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Match> getMatches() {
        List<Match> matches = new ArrayList<>();
        matches.addAll(getHorizontalMatches());
        matches.addAll(getVerticalMatches());
        int matchIndex = 0;
        while (matchIndex < matches.size()) {
            int intersectingMatchIndex = -1;
            for (int i = matchIndex + 1; i < matches.size(); i++) {
                if (matches.get(matchIndex).intersects(matches.get(i)) && matches.get(matchIndex).getToken() == matches.get(i).getToken()) {
                    intersectingMatchIndex = i;
                    break;
                }
            }
            if (intersectingMatchIndex > 0) {
                matchIndex++;
            } else {
                matches.get(matchIndex).addMatch(matches.remove(intersectingMatchIndex));
            }
        }
        return matches;
    }

    private List<Match> getHorizontalMatches() {
        List<Match> matches = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 6; col++) {
                Token token = tokenGrid[row][col];
                if (token != null && token != Token.SAFE && (token == tokenGrid[row][col + 1] && token == tokenGrid[row][col + 2])) {
                    Coordinate start = new Coordinate(row, col);
                    col += 2;
                    while (col < 7 && token == tokenGrid[row][col + 1]) {
                        col++;
                    }
                    matches.add(new Match(Match.HORIZONTAL, start, new Coordinate(row, col), tokenGrid[row][col]));
                }
            }
        }
        return matches;
    }

    private List<Match> getVerticalMatches() {
        List<Match> matches = new ArrayList<>();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 6; row++) {
                Token token = tokenGrid[row][col];
                if (token != null && token != Token.SAFE && (token == tokenGrid[row + 1][col] && token == tokenGrid[row + 2][col])) {
                    Coordinate start = new Coordinate(row, col);
                    row += 2;
                    while (row < 7 && token == tokenGrid[row + 1][col]) {
                        row++;
                    }
                    matches.add(new Match(Match.VERTICAL, start, new Coordinate(row, col), tokenGrid[row][col]));
                }
            }
        }
        return matches;
    }

    public String toString() {
        String s = "GameBoard:[\nturns: " + turns + "\n";
        for (int row = 0; row < tokenGrid.length; row++) {
            for (int col = 0; col < tokenGrid[row].length; col++) {
                s += tokenGrid[row][col].name();
                if (col != tokenGrid[row].length - 1) {
                    s += " ";
                }
            }
            s += "\n";
        }
        s += "]";
        return s;
    }

    public GameBoard deepClone() {
        Token[][] tokenGridClone = new Token[8][8];
        for (int row = 0; row < 8; row++) {
            tokenGridClone[row] = Arrays.copyOf(tokenGrid[row], 8);
        }
        try {
            return new GameBoard(turns, tokenGridClone);
        } catch (IllegalBoardStateException e) {
            throw new IllegalArgumentException(); // this should never be thrown, since it's impossible to create a
                                                  // board with an illegal state.
        }

    }

    private boolean isLegal(Turn turn) {
        Token temp = tokenGrid[turn.getCoordinate1().getRow()][turn.getCoordinate1().getCol()];
        tokenGrid[turn.getCoordinate1().getRow()][turn.getCoordinate1().getCol()] = tokenGrid[turn.getCoordinate2().getRow()][turn.getCoordinate2().getCol()];
        tokenGrid[turn.getCoordinate2().getRow()][turn.getCoordinate2().getCol()] = temp;
        boolean isLegal = matchExists();
        tokenGrid[turn.getCoordinate2().getRow()][turn.getCoordinate2().getCol()] = tokenGrid[turn.getCoordinate1().getRow()][turn.getCoordinate1().getCol()];
        tokenGrid[turn.getCoordinate1().getRow()][turn.getCoordinate1().getCol()] = temp;
        return isLegal;
    }

    public List<Turn> getAllTurns() {
        List<Turn> legalTurns = new ArrayList<>();
        // vertical turns
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 8; col++) {
                Turn turn = new Turn(new Coordinate(row, col), new Coordinate(row + 1, col));
                if (isLegal(turn)) {
                    legalTurns.add(turn);
                }
            }
        }
        // horizontal turns
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 7; col++) {
                Turn turn = new Turn(new Coordinate(row, col), new Coordinate(row, col + 1));
                if (isLegal(turn)) {
                    legalTurns.add(turn);
                }
            }
        }
        return legalTurns;
    }

    private void compressMatch(Match match) {
        compressMatch(match, match.getRandomCoordinate());
    }

    private void compressMatch(Match match, Coordinate upgradeCoordinate) {
        for(Coordinate matchedCoordinate : match.getCoordinates()) {
            tokenGrid[matchedCoordinate.getRow()][matchedCoordinate.getCol()] = null;
        }
        tokenGrid[upgradeCoordinate.getRow()][upgradeCoordinate.getCol()] = TokenUpgradeUtil.getNext(match.getToken());

    }

    private void applyGravity() {
        for(int row = 6; row >=0; row --) {
            for(int col = 0; col < 8; col ++) {
                if(tokenGrid[row][col] != null && tokenGrid[row+1][col] == null) {
                    int destinationRow = row+1;
                    while(destinationRow < 8 && tokenGrid[destinationRow+1][col] == null) {
                        destinationRow++;
                    }
                    tokenGrid[destinationRow][col] = tokenGrid[row][col];
                }
            }
        }
    }

    private void fillRandomly() {
        //TODO get these numbers
        for(int row = 0; row < 8; row ++) {
            for(int col = 0; col < 8; col ++) {
                if(tokenGrid[row][col] == null) {
                    int random = RandomUtil.randomInt(10);
                    Token token = null;
                    if(random < 3) {
                        token = Token.COPPER;
                    } else if(random < 6) {
                        token = Token.SILVER;
                    } else if(random < 9) {
                        token = Token.GOLD;
                    } else {
                        token = Token.BAG;
                    }
                    tokenGrid[row][col] = token;
                }
            }
        }
        
    }

    public void doTurn(Turn turn) throws MatchDoesNotContainSingleTurnCoordinateException {
        int largestMatch = 0;
        //swap
        Token temp = tokenGrid[turn.getCoordinate1().getRow()][turn.getCoordinate1().getCol()];
        tokenGrid[turn.getCoordinate1().getRow()][turn.getCoordinate1().getCol()] = tokenGrid[turn.getCoordinate2().getRow()][turn.getCoordinate2().getCol()];
        tokenGrid[turn.getCoordinate2().getRow()][turn.getCoordinate2().getCol()] = temp;
        //combine matches involved in swap upgrading to spots in the turn
        List<Match> matches = getMatches();
        for(Match match : matches) {
            largestMatch = Math.max(largestMatch, match.getSize());
            Coordinate upgradeCoordinate = match.getCoordinateInTurn(turn);
            compressMatch(match, upgradeCoordinate);
        }
        //while there are matches
        while(matchExists()) {
            do {
                for(Match match : matches) {
                    largestMatch = Math.max(largestMatch, match.getSize());
                    compressMatch(match);
                }
            } while (matchExists());
            applyGravity();
            fillRandomly();
        }
        
        //change turn counter
        if(largestMatch >=5) {
            turns++;
        } else if(largestMatch <= 3) {
            turns--;
        }
    }
    
}
