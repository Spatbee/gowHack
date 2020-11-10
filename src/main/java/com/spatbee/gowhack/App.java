package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.ReadException;

public class App {
    public static void main(String[] args) throws AWTException, IOException, ReadException, IllegalBoardStateException
    {
        GameBoard gameBoard = ScreenReader.readGameBoardFromScreen();
        System.out.println(gameBoard);
    }
}
