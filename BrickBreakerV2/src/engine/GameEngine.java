package engine;

import entities.Ball;
import entities.Boundary;
import entities.Bricks;
import entities.Paddle;

import java.awt.Color;
import java.awt.Graphics2D;

import util.GameConstants;

import enums.GameStatus;

public class GameEngine {

    private Ball ball;
    private Paddle paddle;
    private Bricks bricks;

    private Boundary topBoundary;
    private Boundary rightBoundary;
    private Boundary leftBoundary;

    private CollisionManager collisionManager;

    public GameEngine() {
        this.ball = new Ball(Color.WHITE, GameConstants.BALL_START_POSITION_X, GameConstants.BALL_START_POSITION_Y);
        this.paddle = new Paddle(GameConstants.PADDLE_START_POSITION_X);
        this.bricks = new Bricks(GameConstants.BRICK_ROWS, GameConstants.BRICK_COLUMNS);

        this.topBoundary = new Boundary(Color.GREEN, GameConstants.TOP_BOUNDARY_POSITION_X, GameConstants.TOP_BOUNDARY_POSITION_Y, GameConstants.TOP_BOUNDARY_WIDTH, GameConstants.TOP_BOUNDARY_HEIGHT);
        this.rightBoundary = new Boundary(Color.GREEN, GameConstants.RIGHT_BOUNDARY_POSITION_X, GameConstants.RIGHT_BOUNDARY_POSITION_Y, GameConstants.RIGHT_BOUNDARY_WIDTH, GameConstants.RIGHT_BOUNDARY_HEIGHT);
        this.leftBoundary = new Boundary(Color.GREEN, GameConstants.LEFT_BOUNDARY_POSITION_X, GameConstants.LEFT_BOUNDARY_POSITION_Y, GameConstants.LEFT_BOUNDARY_WIDTH, GameConstants.LEFT_BOUNDARY_HEIGHT);
        this.collisionManager = new CollisionManager(this.ball, this.paddle, this.bricks, this.topBoundary, this.rightBoundary, this.leftBoundary);
    }

    public void render(Graphics2D graphics2D) {
        ball.render(graphics2D);
        paddle.render(graphics2D);
        bricks.render(graphics2D);

        topBoundary.render(graphics2D);
        rightBoundary.render(graphics2D);
        leftBoundary.render(graphics2D);
    }

    private GameStatus status = GameStatus.READY;

    public void update() {
        if (status != GameStatus.PLAYING) {
            return;
        }

        collisionManager.update();

        ball.update();

        if (bricks.getRemainingBricks() == 0) {
            status = GameStatus.WON;
        } else if (ball.getPositionY() > (GameConstants.PADDLE_POSITION_Y + GameConstants.PADDLE_HEIGHT)) {
            status = GameStatus.LOST;
        }
    }

    public void movePaddleRight() {
        paddle.moveRight();
        if (status == GameStatus.READY) {
            ball.moveWithPaddle(paddle);
        }
    }

    public void movePaddleLeft() {
        paddle.moveLeft();
        if (status == GameStatus.READY) {
            ball.moveWithPaddle(paddle);
        }
    }

    public void startGame() {
        if (status == GameStatus.READY) {
            status = GameStatus.PLAYING;
        }
    }

    public void restartGame() {
        status = GameStatus.READY;

        ball.reset();
        paddle.reset();
        bricks = new Bricks(GameConstants.BRICK_ROWS, GameConstants.BRICK_COLUMNS);

        topBoundary.reset();
        rightBoundary.reset();
        leftBoundary.reset();

        collisionManager = new CollisionManager(ball, paddle, bricks, topBoundary, rightBoundary, leftBoundary);
    }

    public boolean isReady() {
        return status == GameStatus.READY;
    }

    public boolean hasWon() {
        return status == GameStatus.WON;
    }

    public boolean hasLost() {
        return status == GameStatus.LOST;
    }

    public int getTotalBricks() {
        return bricks.getRemainingBricks();
    }

    public int getScore() {
        return bricks.getScore();
    }
}
