package dev.momo.snake.ui;

import dev.momo.snake.SnakeGame;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private final SnakeGame game;

    public GameFrame(SnakeGame game) {
        this.game = game;
        setSize(new Dimension(518+17, 541+17));
        setLocationRelativeTo(null);
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

}
