import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public static Token[][] getTokenGrid() throws AWTException, IOException {
        Token[][] tokenGrid = new Token[8][8];
        BufferedImage boardImage = getRobot().createScreenCapture(new Rectangle(TOP_LEFT_X, TOP_LEFT_Y, WIDTH, HEIGHT));
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                tokenGrid[row][col] = TokenIdentifier.identifyToken(
                    boardImage, 
                    col * WIDTH / 8,
                    row * HEIGHT / 8,
                    WIDTH / 8,
                    HEIGHT / 8
                );
            }
        }
        return tokenGrid;
    }
    public static void main(String[] args) throws AWTException, IOException {
        long start = System.currentTimeMillis();
        Token[][] tokenGrid = getTokenGrid();
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                System.out.print(tokenGrid[x][y].name() + " ");
            }
            System.out.println();
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
    }
}