package dev.momo.snake.ui;

import dev.momo.snake.SnakeFace;
import dev.momo.snake.SnakeGame;
import dev.momo.snake.io.StatsLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private final SnakeGame game;
    private final GameFrame frame;

    private String nameInput = "";

    public GamePanel(SnakeGame game, GameFrame frame) {
        this.game = game;
        this.frame = frame;

        setSize(frame.getSize());
        setLocation(0, 0);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        addKeyListener(this);
        frame.addKeyListener(this);
        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;

        int tileSize = 15;
        int gapSize = 2;
        int tileCount = 31;
        g.setColor(new Color(70, 70, 70));


        for (int x = 0; x < tileCount; x++) {
            for (int y = 0; y < tileCount; y++) {

                Point tile = new Point(x, y);
                boolean top = false;
                boolean food = false;

                for (int i = 0; i < game.getSnakeSize(); i++) {

                    try {

                        if (game.getSnakeTiles().get(i).equals(tile)) {

                            if (i == 0) {
                                g.setColor(Color.ORANGE);
                                top = true;
                            } else {
                                g.setColor(Color.GRAY);
                            }

                        }
                    } catch (Exception ex) {

                    }
                }

                if ((x == 0 || x == tileCount - 1) || (y == 0 || y == tileCount - 1)) {
                    g.setColor(new Color(80, 80, 80));
                }

                if (game.getFood().equals(tile)) {
                    g.setColor(Color.RED);
                    food = true;
                }

                g.fillRect(x * tileSize + (x * gapSize) + gapSize, y * tileSize + y * gapSize + gapSize, tileSize, tileSize);

                int textX = x * tileSize + (x * gapSize) + gapSize + tileSize + gapSize;
                int textY = y * tileSize + y * gapSize + gapSize + tileSize + gapSize - 10;

                g.setColor(new Color(70, 70, 70));
            }
        }


        if (game.isFirstTime()) {
            int x = getWidth() / 6;
            int y = getHeight() / 9;
            int w = (getWidth() / 6) * 4;
            int h = 125;

            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(100, 100, 100));
            g.fillRect(x, y, w, h);

            g.setColor(Color.LIGHT_GRAY);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setFont(g.getFont().deriveFont(18f));
            g.drawString("Welcome to SnakeJava", x + 15, y + 20);
            g.setFont(g.getFont().deriveFont(14f));
            g.drawString("Please type your name here, then press [ENTER]:", x + 15, y + 40);

            g.drawRect(x + 15, y + 55, w - 30, 30);
            g.setFont(g.getFont().deriveFont(28f).deriveFont(Font.BOLD));
            g.drawString(nameInput, x + 17, y + 56 + 25);

            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.BOLD));
            g.drawString("[ENTER] to submit!", x + 15, y + 115);
        } else if (game.shouldShowTutorial()) {

            int x = getWidth() / 6;
            int y = getHeight() / 9;
            int w = (getWidth() / 6) * 4;
            int h = 125;

            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(100, 100, 100));
            g.fillRect(x, y, w, h);

            g.setColor(Color.LIGHT_GRAY);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setFont(g.getFont().deriveFont(18f));
            g.drawString("Welcome to SnakeJava", x + 15, y + 20);
            g.setFont(g.getFont().deriveFont(14f));
            g.drawString("Move Snake (GOLDEN) around with WASD / Arrows.", x + 15, y + 40);
            g.setFont(g.getFont().deriveFont(14f));
            g.drawString("Get food (RED) to make Snake bigger and faster.", x + 15, y + 60);
            g.setFont(g.getFont().deriveFont(14f));
            g.drawString("Avoid walls or eating yourself.", x + 15, y + 80);
            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.BOLD));
            g.drawString("[ENTER] to start game!", x + 15, y + 115);
        } else if (game.shouldShowResults()) {
            int x = getWidth() / 6;
            int y = getHeight() / 9;
            int w = (getWidth() / 6) * 4;
            int h = 125;

            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(100, 100, 100));
            g.fillRect(x, y, w, h);

            g.setColor(Color.LIGHT_GRAY);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setFont(g.getFont().deriveFont(18f));
            g.drawString("Snake has died.", x + 15, y + 20);
            g.setFont(g.getFont().deriveFont(14f));
            g.drawString("The game is over.", x + 15, y + 40);
            g.setFont(g.getFont().deriveFont(14f));

            String txt1 = "Your final score for this round is: ";
            int txt1w = g.getFontMetrics().stringWidth(txt1);

            g.drawString(txt1, x + 15, y + 60);
            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.BOLD));
            g.drawString("" + game.getScore() + " points", x + 15 + txt1w, y + 60);

            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.PLAIN));
            String txt2 = "Snake has traveled a total of: ";
            int txt2w = g.getFontMetrics().stringWidth(txt2);

            g.drawString(txt2, x + 15, y + 80);
            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.BOLD));
            g.drawString("" + game.getSnakeTiles().size() + " tiles", x + 15 + txt2w, y + 80);
            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.BOLD));
            g.drawString("[ENTER] to try again!", x + 15, y + 115);
            int x1 = getWidth() / 6;
            int y1 = getHeight() / 9 + 140;
            int w1 = (getWidth() / 6) * 4;
            int h1 = 30;

            g.setColor(new Color(100, 100, 100));
            g.fillRect(x1, y1, w1, h1);
            g.setFont(g.getFont().deriveFont(14f).deriveFont(Font.BOLD));

            g.setColor(Color.LIGHT_GRAY);
            String text = "Your high score: ";

            if (game.getStatsLoader().getHighScore() > game.getScore()) {
                text+=game.getStatsLoader().getHighScore() + " points";
            } else {
                text+=game.getScore() + " points";
            }

            g.drawString(text, x1 + w1 / 2 - (g.getFontMetrics().stringWidth(text) / 2), y1 + h1 / 2 + 5);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (game.isFirstTime()) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER && nameInput.length() > 0) {
                //submit

                game.getStatsLoader().setName(nameInput);
                game.setFirstTime(false);

                return;
            }

            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                if (nameInput.length() > 0)
                    nameInput = nameInput.substring(0, nameInput.length() - 1);

                return;
            }

            if ((Character.isAlphabetic(e.getKeyChar()) || Character.isDigit(e.getKeyChar())) && nameInput.length() < 10) {
                nameInput += e.getKeyChar();
                nameInput = nameInput.toUpperCase();

                return;
            }

            return;

        }

        if (game.shouldShowTutorial()) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                game.setTutorial(false);
            }

            return;
        }

        if (game.shouldShowResults()) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                game.defaultSnake();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }

            return;
        }

        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            game.setDirection(SnakeFace.NORTH);
        } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
            game.setDirection(SnakeFace.WEST);
        } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            game.setDirection(SnakeFace.EAST);
        } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
            game.setDirection(SnakeFace.SOUTH);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
