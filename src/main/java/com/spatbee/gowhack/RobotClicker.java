package com.spatbee.gowhack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class RobotClicker {

    private static Robot robot;

    private static Robot getRobot() throws AWTException {
        if (robot == null) {
            robot = new Robot();
        }
        return robot;
    }

    public static void executeTurn(Turn turn) throws AWTException {
        Coordinate start = turn.getCoordinate1();
        Coordinate end = turn.getCoordinate2();
        if (RandomUtil.randomInt(2) < 1) { // 50% chance to swap the order
            start = turn.getCoordinate2();
            end = turn.getCoordinate1();
        }

        clickAtBoardCoordinates(start);
        try {
            Thread.sleep(150 + RandomUtil.randomInt(400));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickAtBoardCoordinates(end);
    }

    private static void clickAtBoardCoordinates(Coordinate coordinate) throws AWTException {
        int tokenWidth = Coordinates.BOARD_WIDTH / 8;
        int tokenHeight = Coordinates.BOARD_HEIGHT / 8;
        int bufferWidth = tokenWidth / 6;
        int bufferHeight = tokenHeight / 6;
        getRobot().mouseMove(Coordinates.BOARD_TOP_LEFT_X +  coordinate.getCol() * tokenWidth + bufferWidth + RandomUtil.randomInt(bufferWidth * 4), Coordinates.BOARD_TOP_LEFT_Y + coordinate.getRow() * tokenHeight + bufferHeight + RandomUtil.randomInt(bufferHeight * 4));
        getRobot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        getRobot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
}
