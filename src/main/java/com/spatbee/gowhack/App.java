package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

import com.spatbee.gowhack.exception.ReadException;

public class App {
    public static void main(String[] args) throws AWTException, IOException, ReadException
    {
        long start = System.currentTimeMillis();
        System.out.println(ScreenReader.readGameBoardFromScreen());
        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        System.out.println(ScreenReader.readGameBoardFromScreen());
        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
