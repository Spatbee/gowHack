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
        GameBoard gameBoard = ScreenReader.readGameBoardFromScreen();
        System.out.println(gameBoard);
        gameBoard.doTurn(new Turn(new Coordinate(2, 4), new Coordinate(2, 5)));
        System.out.println(gameBoard);
        
    }
}
