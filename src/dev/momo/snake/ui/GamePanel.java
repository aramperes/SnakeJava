package dev.momo.snake.ui;

import dev.momo.snake.SnakeFace;
import dev.momo.snake.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{

    private final SnakeGame game;
    private final GameFrame frame;

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

                for (int i = 0; i < game.getSnakeSize(); i++) {

                    try {

                        if (game.getSnakeTiles().get(i).equals(tile)) {
                            g.setColor(Color.GRAY);
                        }
                    } catch(IndexOutOfBoundsException ex) {

                    }
                }

                if ((x == 0 || x == tileCount-1) || (y == 0 || y == tileCount-1)) {
                    g.setColor(Color.GRAY);
                }

                if (game.getFood().equals(tile)) {
                    g.setColor(Color.GRAY);
                }

                g.fillRect(x * tileSize + (x*gapSize) + gapSize, y * tileSize + y*gapSize + gapSize, tileSize, tileSize);
                g.setColor(new Color(70, 70, 70));
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            game.setDirection(SnakeFace.NORTH);
        } else if (e.getKeyChar() == 'a') {
            game.setDirection(SnakeFace.WEST);
        } else if (e.getKeyChar() == 'd') {
            game.setDirection(SnakeFace.EAST);
        } else if (e.getKeyChar() == 's') {
            game.setDirection(SnakeFace.SOUTH);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
