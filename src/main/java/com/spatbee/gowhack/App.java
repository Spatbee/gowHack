package com.spatbee.gowhack;

import java.awt.AWTException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws AWTException, IOException {
        // Token[][] tokenGrid = {
        //     {Token.BAG, Token.SILVER, Token.BAG, Token.SILVER, Token.COPPER, Token.BAG, Token.SILVER, Token.SILVER},
        //     {Token.GOLD, Token.COPPER, Token.SILVER, Token.COPPER, Token.COPPER, Token.SILVER, Token.GOLD, Token.COPPER},
        //     {Token.GOLD, Token.SILVER, Token.GOLD, Token.COPPER, Token.SILVER, Token.BAG, Token.SILVER, Token.SILVER},
        //     {Token.BAG, Token.GOLD, Token.SILVER, Token.BAG, Token.BAG, Token.SILVER, Token.SILVER, Token.BAG},
        //     {Token.COPPER, Token.SILVER, Token.SILVER, Token.GOLD, Token.SILVER, Token.COPPER, Token.BAG, Token.SILVER},
        //     {Token.BAG, Token.COPPER, Token.GOLD, Token.GOLD, Token.COPPER, Token.GOLD, Token.SILVER, Token.BAG},
        //     {Token.GOLD, Token.SILVER, Token.SILVER, Token.COPPER, Token.SILVER, Token.BAG, Token.BAG, Token.COPPER},
        //     {Token.COPPER, Token.BAG, Token.BAG, Token.COPPER, Token.COPPER, Token.BAG, Token.SILVER, Token.BAG}
        // };
        // GameBoard gameBoard = new GameBoard(12, tokenGrid);
        GamePlayer.playGame();
    }
}
