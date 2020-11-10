package com.spatbee.gowhack;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.spatbee.gowhack.exception.ColorDoesNotMatchWellException;
import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.ReadException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ScreenReader {

    private static Robot robot;
    private static Tesseract tesseract;

    private ScreenReader() {
        // private constructor to hide implict public one
    }

    private static Robot getRobot() throws AWTException {
        if (robot == null) {
            robot = new Robot();
        }
        return robot;
    }

    private static Tesseract getTesseract() {
        if (tesseract == null) {
            tesseract = new Tesseract();
            tesseract.setDatapath(".");
        }
        return tesseract;
    }

    private static Token[][] getTokenGrid() throws AWTException, IOException, ReadException {
        Token[][] tokenGrid = new Token[8][8];
        BufferedImage boardImage = getRobot().createScreenCapture(new Rectangle(Coordinates.BOARD_TOP_LEFT_X,
                Coordinates.BOARD_TOP_LEFT_Y, Coordinates.BOARD_WIDTH, Coordinates.BOARD_HEIGHT));
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                try {
                    tokenGrid[row][col] = TokenIdentifier.identifyToken(boardImage, col * Coordinates.BOARD_WIDTH / 8,
                            row * Coordinates.BOARD_HEIGHT / 8, Coordinates.BOARD_WIDTH / 8,
                            Coordinates.BOARD_HEIGHT / 8);
                } catch (ColorDoesNotMatchWellException e) {
                    throw new ReadException();
                }
            }
        }
        return tokenGrid;
    }

    private static int getTurnCount() throws AWTException, ReadException {
        BufferedImage turnImage = getRobot().createScreenCapture(new Rectangle(Coordinates.TURN_TOP_LEFT_X,
                Coordinates.TURN_TOP_LEFT_Y, Coordinates.TURN_WIDTH, Coordinates.TURN_HEIGHT));
        try {
            return Integer.parseInt(getTesseract().doOCR(turnImage).trim());
        } catch (TesseractException | NumberFormatException e) {
            throw new ReadException();
        }
    }

    public static GameBoard readGameBoardFromScreen() throws AWTException, IOException, ReadException, IllegalBoardStateException {
        return new GameBoard(getTurnCount(), getTokenGrid());
    }

}