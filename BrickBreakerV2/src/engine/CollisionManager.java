package engine;

import entities.Bricks;
import entities.Ball;
import entities.Boundary;
import entities.Brick;
import entities.Paddle;

import util.GameConstants;

public class CollisionManager {

    private Ball ball;
    private Paddle paddle;
    private Bricks bricks;
    private Boundary topBoundary;
    private Boundary rightBoundary;
    private Boundary leftBoundary;

    public CollisionManager(Ball ball, Paddle paddle, Bricks bricks, Boundary topBoundary, Boundary rightBoundary, Boundary leftBoundary) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
        this.topBoundary = topBoundary;
        this.rightBoundary = rightBoundary;
        this.leftBoundary = leftBoundary;
    }

    public void update() {
        ballCollidePaddle();
        ballCollideBricks();
        ballCollideTopBoundary();
        ballCollideRightBoundary();
        ballCollideLeftBoundary();
    }

    private void ballCollidePaddle() {
        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.reverseVelocityY();
        }
    }

    private void ballCollideBricks() {
        for (Brick brick : bricks.getBricks()) {
            if (ball.getBounds().intersects(brick.getBounds())) {

                bricks.destroyBrick(brick);
                ball.setColor(brick.getColor());

                int ballLeft = ball.getPositionX();
                int ballRight = ball.getPositionX() + GameConstants.BALL_SIZE;

                int brickLeft = brick.getPositionX();
                int brickRight = brickLeft + GameConstants.BRICK_WIDTH;

                if (ballRight <= brickLeft + 2 || ballLeft >= brickRight - 2) {
                    ball.reverseVelocityX();
                } else {
                    ball.reverseVelocityY();
                }
                break;
            }
        }
    }

    private void ballCollideTopBoundary() {
        if (ball.getBounds().intersects(topBoundary.getBounds())) {
            ball.reverseVelocityY();
            topBoundary.changeColor();
        }
    }

    private void ballCollideRightBoundary() {
        if (ball.getBounds().intersects(rightBoundary.getBounds())) {
            ball.reverseVelocityX();
            rightBoundary.changeColor();
        }
    }

    private void ballCollideLeftBoundary() {
        if (ball.getBounds().intersects(leftBoundary.getBounds())) {
            ball.reverseVelocityX();
            leftBoundary.changeColor();
        }
    }
}
