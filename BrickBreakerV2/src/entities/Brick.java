package entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.GameConstants;

public class Brick {

    private final Color color;

    private final int positionX;
    private final int positionY;

    public Brick(Color color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Color getColor() {
        return color;
    }

    public int getPositionX() {
        return positionX;
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(positionX, positionY, GameConstants.BRICK_WIDTH, GameConstants.BRICK_HEIGHT);
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(Color.WHITE);
        graphics.drawRect(positionX, positionY, GameConstants.BRICK_WIDTH, GameConstants.BRICK_HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, GameConstants.BRICK_WIDTH, GameConstants.BRICK_HEIGHT);
    }
}
