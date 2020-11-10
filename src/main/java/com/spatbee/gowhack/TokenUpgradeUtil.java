package com.spatbee.gowhack;

public class TokenUpgradeUtil {
    private static final Token[] upgradeOrder = {Token.COPPER, Token.SILVER, Token.GOLD, Token.BAG, Token.WOOD, Token.EMERALD, Token.RUBY, Token.SAFE};

    public static Token getNext(Token token) {
        for(int i = 0; i < 7; i++) {
            if(token == upgradeOrder[i]) {
                return upgradeOrder[i+1];
            }
        }
        return null;
    }
}
