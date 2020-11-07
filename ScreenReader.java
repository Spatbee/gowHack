import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenReader {

    private static final int TOP_LEFT_X = 484;
    private static final int TOP_LEFT_Y = 82;
    private static final int WIDTH = 949;
    private static final int HEIGHT = 951;
    
    private static Robot robot;

    private static Robot getRobot() throws AWTException {
        if(robot == null) {
            robot = new Robot();
        }
        return robot;
    }

    public static BufferedImage[][] getTokenGrid() throws AWTException, IOException {
        BufferedImage[][] tokenGrid = new BufferedImage[8][8];
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                tokenGrid[row][col] = getRobot().createScreenCapture(new Rectangle(
                    TOP_LEFT_X + col * WIDTH / 8,
                    TOP_LEFT_Y + row * HEIGHT / 8,
                    WIDTH / 8,
                    HEIGHT / 8
                ));
                ImageIO.write(tokenGrid[row][col], "png", new File("token.row" + row + ".col" + col +".png"));
            }
        }
        return tokenGrid;
    }
    public static void main(String[] args) throws AWTException, IOException {
        getTokenGrid();
    }
}