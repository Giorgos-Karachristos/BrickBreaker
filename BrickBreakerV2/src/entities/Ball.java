package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.GameConstants;

public class Ball {

    private Color color;

    private int positionX;
    private int positionY;

    private int velocityX;
    private int velocityY;

    public Ball(Color color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = GameConstants.BALL_VELOCITY_X;
        this.velocityY = GameConstants.BALL_VELOCITY_Y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }

    public void reverseVelocityX() {
        this.velocityX = -this.velocityX;
    }

    public void reverseVelocityY() {
        this.velocityY = -this.velocityY;
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillOval(positionX, positionY, GameConstants.BALL_SIZE, GameConstants.BALL_SIZE);
    }

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, GameConstants.BALL_SIZE, GameConstants.BALL_SIZE);
    }

    public void reset() {
        this.color = Color.WHITE;
        this.positionX = GameConstants.BALL_START_POSITION_X;
        this.positionY = GameConstants.BALL_START_POSITION_Y;
        this.velocityX = GameConstants.BALL_VELOCITY_X;
        this.velocityY = GameConstants.BALL_VELOCITY_Y;
    }

    public void moveWithPaddle(Paddle paddle) {
        positionX = paddle.getPositionX() + GameConstants.BALL_PADDLE_OFFSET;
    }
}
