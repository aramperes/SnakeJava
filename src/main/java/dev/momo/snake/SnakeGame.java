package dev.momo.snake;

import dev.momo.snake.io.StatsLoader;
import dev.momo.snake.ui.GameFrame;
import dev.momo.snake.ui.GamePanel;
import dev.momo.snake.ui.RenderThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

    private int snakeSize = 10;
    private Point food;
    private int speed = 0;
    private boolean tutorial = true;
    private boolean results = false;

    private final GameFrame frame;
    private final GamePanel panel;
    private final Random random = new Random();
    private List<Point> snakeTiles = new ArrayList<>();

    private boolean firstTime;
    public int cache_hs;
    private final StatsLoader statsLoader;

    private SnakeFace direction = SnakeFace.NONE;

    public SnakeGame() {
        statsLoader = new StatsLoader();
        statsLoader.test();
        firstTime = statsLoader.getName() == null;
        cache_hs = statsLoader.getHighScore();
        frame = new GameFrame(this);
        panel = new GamePanel(this, frame);
        defaultSnake();

        new TickThread(this).start();
        new RenderThread(this).start();
    }

    public void defaultSnake() {
       // for (int i = 0; i < snakeSize; i++)
        setResults(false);
        snakeTiles.clear();
        snakeTiles.add(new Point(15, 15));
        direction = SnakeFace.NONE;
        snakeSize = 10;
        speed = 0;
        food = new Point(random.nextInt(28) + 1, random.nextInt(28) + 1);
       // }
    }

    public void executeRender() {
        panel.repaint();
    }

    public void executeTick(int speed) {

        this.speed = speed;
        frame.setTitle("Snake - Speed: " + speed + " tiles/s - Score: " + (snakeSize-10) + " points");

        if (food == null)
            return;

        if (this.snakeTiles.size() == 0)
            return;

        if (direction == SnakeFace.NONE)
            return;

        List<Point> newTiles = new ArrayList<>();
        Point newTile = (new Point(snakeTiles.get(0).x + direction.getX(), snakeTiles.get(0).y + direction.getY()));

        boolean collide = false;

        for (int i = 0; i < snakeSize; i++) {
            try {

                if (getSnakeTiles().get(i).equals(newTile)) {
                    collide = true;
                }

                int x = getSnakeTiles().get(i).x;
                int y = getSnakeTiles().get(i).y;

                if ((x == 0 || x == 31-1) || (y == 0 || y == 31-1)) {
                    collide = true;
                }
            } catch(IndexOutOfBoundsException ex) {

            }
        }

        if (collide) {
            //JOptionPane.showConfirmDialog(frame, "ded bro, score=" + (snakeSize-10), "", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            //System.exit(0);

            int score = snakeSize - 10;
            int distance = snakeTiles.size();
            //JOptionPane.showMessageDialog(panel, "You died.\nYour final score was " + score + " points.\nYou traveled " + distance + " tiles in total.");
            //defaultSnake();
            setResults(true);

            if (getStatsLoader().getHighScore() < getScore()) {
                getStatsLoader().setHighScore(getScore());
            }
            return;
        }

        newTiles.add(newTile);
        newTiles.addAll(this.snakeTiles);

        snakeTiles = newTiles;

        if (newTile.equals(food)) {
            snakeSize++;
            while(!findNewFood());
        }
;
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

    public int getScore() {
        return snakeSize - 10;
    }

    public boolean shouldShowTutorial() {
        return tutorial;
    }

    public void setTutorial(boolean tutorial) {
        this.tutorial = tutorial;
    }

    public boolean shouldShowResults() {
        return results;
    }

    public void setResults(boolean results) {
        this.results = results;
    }

    public StatsLoader getStatsLoader() {
        return statsLoader;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
