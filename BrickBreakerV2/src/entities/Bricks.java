package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GameConstants;

public class Bricks {

    private ArrayList<Brick> bricks;
    private int score;

    public Bricks(int row, int col) {
        bricks = new ArrayList<>();
        score = 0;

        Color brickColor;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == j || i + 2 == j || i + 4 == j || i - 2 == j || i - 4 == j) {
                    brickColor = Color.orange;
                } else {
                    brickColor = Color.cyan;
                }

                bricks.add(new Brick(brickColor, j * GameConstants.BRICK_WIDTH + 80, i * GameConstants.BRICK_HEIGHT + 50));
            }
        }
    }

    public List<Brick> getBricks() {
        return Collections.unmodifiableList(bricks);
    }

    public void destroyBrick(Brick brick) {
        if (bricks.remove(brick)) {
            score += 5;
        }
    }

    public int getRemainingBricks() {
        return bricks.size();
    }

    public int getScore() {
        return score;
    }

    public void render(Graphics2D graph) {
        for (Brick brick : bricks) {
            brick.render(graph);
        }
    }
}
