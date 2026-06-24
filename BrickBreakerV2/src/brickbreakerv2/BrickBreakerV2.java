package brickbreakerv2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import ui.Game;

public class BrickBreakerV2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            Game game = new Game();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1021, 689);
            frame.setLocationRelativeTo(null);
            frame.setTitle("Brick Breaker");
            frame.setResizable(false);
            frame.setContentPane(game);
            frame.setVisible(true);
        });
    }
}
