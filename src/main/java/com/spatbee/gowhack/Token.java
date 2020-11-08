package com.spatbee.gowhack;

public enum Token {
    COPPER(1),
    SILVER(3),
    GOLD(10),
    BAG(30),
    WOOD(80),
    EMERALD(200),
    RUBY(400),
    SAFE(1000);

    private int score;

    private Token(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
