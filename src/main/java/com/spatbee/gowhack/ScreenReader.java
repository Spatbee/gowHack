package com.spatbee.gowhack;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ScreenReader {

    
    
    private static Robot robot;
    private static Tesseract tesseract;

    private ScreenReader() {
        //private constructor to hide implict public one
    }

    private static Robot getRobot() throws AWTException {
        if(robot == null) {
            robot = new Robot();
        }
        return robot;
    }

    private static Tesseract getTesseract() {
        if(tesseract == null) {
            tesseract = new Tesseract();
            tesseract.setDatapath("."); 
        }
        return tesseract;
    }

    public static Token[][] getTokenGrid() throws AWTException, IOException {
        Token[][] tokenGrid = new Token[8][8];
        BufferedImage boardImage = getRobot().createScreenCapture(new Rectangle(Coordinates.BOARD_TOP_LEFT_X, Coordinates.BOARD_TOP_LEFT_Y, Coordinates.BOARD_WIDTH, Coordinates.BOARD_HEIGHT));
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                tokenGrid[row][col] = TokenIdentifier.identifyToken(
                    boardImage, 
                    col * Coordinates.BOARD_WIDTH / 8,
                    row * Coordinates.BOARD_HEIGHT / 8,
                    Coordinates.BOARD_WIDTH / 8,
                    Coordinates.BOARD_HEIGHT / 8
                );
            }
        }
        return tokenGrid;
    }

    public static void getTurnImage() throws AWTException, IOException {
        BufferedImage turnImage = getRobot().createScreenCapture(new Rectangle(Coordinates.TURN_TOP_LEFT_X, Coordinates.TURN_TOP_LEFT_Y, Coordinates.TURN_WIDTH, Coordinates.TURN_HEIGHT));
        try {
            System.out.println(getTesseract().doOCR(turnImage));
        } catch (TesseractException e) {
            System.out.println("failed to read text");
        }
    }

}