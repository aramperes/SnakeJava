package dev.momo.snake;

import dev.momo.snake.ui.GameFrame;
import dev.momo.snake.ui.GamePanel;
import dev.momo.snake.ui.RenderThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

    private int snakeSize = 20;
    private Point food;

    private final GameFrame frame;
    private final GamePanel panel;
    private final Random random = new Random();
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
        food = new Point(random.nextInt(28) + 1, random.nextInt(28) + 1);
       // }
    }

    public void executeRender() {
        panel.repaint();
    }

    public void executeTick() {

        if (food == null)
            return;

        if (this.snakeTiles.size() == 0)
            return;

        if (direction == SnakeFace.NONE)
            return;

        List<Point> newTiles = new ArrayList<>();
        Point newTile = (new Point(snakeTiles.get(0).x + direction.getX(), snakeTiles.get(0).y + direction.getY()));
        newTiles.add(newTile);
        newTiles.addAll(this.snakeTiles);

        if (newTile.equals(food)) {
            snakeSize++;
            while(!findNewFood());
        }

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

    public Point getFood() {
        return food;
    }

    private boolean findNewFood() {
        Point f = new Point(random.nextInt(28) + 1, random.nextInt(28) + 1);

        for (int i = 0; i < snakeSize; i++) {
            try {

                if (getSnakeTiles().get(i).equals(f)) {
                    return false;
                }
            } catch(IndexOutOfBoundsException ex) {

            }
        }

        food = f;
        return true;
    }
}
