package dev.momo.snake;

import dev.momo.snake.ui.GameFrame;
import dev.momo.snake.ui.GamePanel;
import dev.momo.snake.ui.RenderThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeGame {

    private int snakeSize = 5;

    private final GameFrame frame;
    private final GamePanel panel;
    private List<Point> snakeTiles = new ArrayList<>();

    private SnakeFace direction = SnakeFace.NONE;

    public SnakeGame() {
        frame = new GameFrame(this);
        panel = new GamePanel(this, frame);
        defaultSnake();

        new TickThread(this).start();
        new RenderThread(this).start();
    }

    private void defaultSnake() {
       // for (int i = 0; i < snakeSize; i++) {
            snakeTiles.add(new Point(15, 15));
       // }
    }

    public void executeRender() {
        panel.repaint();
    }

    public void executeTick() {

        if (this.snakeTiles.size() == 0)
            return;

        if (direction == SnakeFace.NONE)
            return;

        List<Point> newTiles = new ArrayList<>();
        newTiles.add(new Point(snakeTiles.get(0).x + direction.getX(), snakeTiles.get(0).y + direction.getY()));
        newTiles.addAll(this.snakeTiles);

        snakeTiles = newTiles;
    }

    public List<Point> getSnakeTiles() {
        return snakeTiles;
    }

    public int getSnakeSize() {
        return snakeSize;
    }

    public SnakeFace getDirection() {
        return direction;
    }

    public void setDirection(SnakeFace direction) {
        this.direction = direction;
    }
}
