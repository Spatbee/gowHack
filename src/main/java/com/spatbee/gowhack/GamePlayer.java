package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.ReadException;

public class GamePlayer {

    public static void playGame() throws AWTException, IOException {
        boolean terminate = false;
        while(!terminate) {
            boolean twoReadsMatch = false;
            GameBoard gameBoard = null;
            int fails = 0;
            while (fails < 20 && !twoReadsMatch) {
                try {
                    GameBoard newGameBoard = ScreenReader.readGameBoardFromScreen();
                    if (newGameBoard.equals(gameBoard)) {
                        twoReadsMatch = true;
                    } else {
                        System.out.println("boards don't match");
                        gameBoard = newGameBoard;
                    }
                } catch (ReadException | IllegalBoardStateException e) {
                    e.printStackTrace();
                    System.out.println("failed to read");
                    fails++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(fails == 20) {
                System.out.println("terminating");
                terminate = true;
            } else {
                RobotClicker.executeTurn(GameCoordinator.getBestTurnMonteCarlo(gameBoard));
            }
        }
    }
    
}
