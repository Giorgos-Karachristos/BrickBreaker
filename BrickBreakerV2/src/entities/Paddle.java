package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.GameConstants;

public class Paddle {

    private int positionX;

    public Paddle(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionX() {
        return positionX;
    }

    public void moveLeft() {
        if (positionX - GameConstants.PADDLE_MOVEMENT < GameConstants.PADDLE_LEFT_BOUNDARY) {
            positionX = GameConstants.PADDLE_LEFT_BOUNDARY;
        } else {
            positionX -= GameConstants.PADDLE_MOVEMENT;
        }
    }

    public void moveRight() {
        if (positionX + GameConstants.PADDLE_MOVEMENT > GameConstants.PADDLE_RIGHT_BOUNDARY) {
            positionX = GameConstants.PADDLE_RIGHT_BOUNDARY;
        } else {
            positionX += GameConstants.PADDLE_MOVEMENT;
        }
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(Color.PINK);
        graphics.fillRect(positionX, GameConstants.PADDLE_POSITION_Y, GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(positionX, GameConstants.PADDLE_POSITION_Y, GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT);
    }

    public void reset() {
        this.positionX = GameConstants.PADDLE_START_POSITION_X;
    }
}
