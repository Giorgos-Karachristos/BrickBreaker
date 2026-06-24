package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boundary {

    private Color color;

    private final int positionX;
    private final int positionY;
    private final int width;
    private final int height;

    public Boundary(Color color, int positionX, int positionY, int width, int height) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(positionX, positionY, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, width, height);
    }

    public void changeColor() {
        if (color == Color.green) {
            color = Color.yellow;
        } else if (color == Color.yellow) {
            color = Color.cyan;
        } else {
            color = Color.green;
        }
    }

    public void reset() {
        this.color = Color.green;
    }
}
