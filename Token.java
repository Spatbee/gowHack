public enum Token {
    COPPER(1),
    SILVER(3),
    GOLD(10),
    BAG(30),
    WOOD(80),
    EMERALD(100),
    RUBY(200),
    SAFE(400);

    private int score;

    private Token(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
