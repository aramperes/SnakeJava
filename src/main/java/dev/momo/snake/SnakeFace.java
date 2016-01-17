package dev.momo.snake;

public enum SnakeFace {

    NORTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, 1),
    NONE(0, 0);


    private int x, y;
    SnakeFace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
