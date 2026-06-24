package ui;

import engine.GameEngine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

    private GameEngine gameEngine;

    private Timer timer;

    public Game() {
        gameEngine = new GameEngine();

        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(7, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        renderBackground(graphics2D);
        
        gameEngine.render(graphics2D);

        renderHUD(graphics2D);

        renderMessages(graphics2D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.update();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameEngine.movePaddleRight();
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            gameEngine.movePaddleLeft();
        }
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            gameEngine.startGame();
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gameEngine.hasWon() || gameEngine.hasLost()) {
                gameEngine.restartGame();
                repaint();
            }
        }
    }

    private void renderBackground(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, 1005, 650);
    }

    private void renderHUD(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(10));

        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(710, 5, 290, 640);
        graphics2D.drawLine(710, 100, 1000, 100);
        graphics2D.drawLine(710, 200, 1000, 200);

        graphics2D.setFont(new Font("Courier", Font.BOLD, 25));
        graphics2D.drawString("Created by: ", 795, 250);
        graphics2D.drawString("Karachristos Georgios", 720, 290);

        graphics2D.setColor(Color.white);
        graphics2D.drawString("Score: " + gameEngine.getScore(), 805, 60);
        graphics2D.drawString("Remaining Bricks: " + gameEngine.getTotalBricks(), 730, 160);
    }

    private void renderMessages(Graphics2D graphics2D) {
        if (gameEngine.isReady()) {
            graphics2D.setColor(Color.magenta);
            graphics2D.setFont(new Font("Courier", Font.BOLD, 25));
            graphics2D.drawString("Press Space to start!", 230, 300);
        }

        if (gameEngine.hasWon() || gameEngine.hasLost()) {
            graphics2D.setColor(Color.red);
            graphics2D.setFont(new Font("Courier", Font.BOLD, 30));
            if (gameEngine.hasWon()) {
                graphics2D.drawString("Congratulations!", 240, 260);
            }
            if (gameEngine.hasLost()) {
                graphics2D.drawString("Game Over!", 280, 260);
            }
            graphics2D.drawString("Score: " + gameEngine.getScore(), 285, 300);
            graphics2D.drawString("Press Enter to Restart", 205, 340);
        }
    }
}
