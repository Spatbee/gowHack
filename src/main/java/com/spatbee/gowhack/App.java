package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws AWTException, IOException
    {
        long start = System.currentTimeMillis();
        Token[][] tokenGrid = ScreenReader.getTokenGrid();
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                System.out.print(tokenGrid[x][y].name() + " ");
            }
            System.out.println();
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
