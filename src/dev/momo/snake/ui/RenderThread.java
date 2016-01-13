package dev.momo.snake.ui;

import dev.momo.snake.SnakeGame;

public class RenderThread extends Thread {

    private final SnakeGame game;

    public RenderThread(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
            game.executeRender();
        }
    }
}
