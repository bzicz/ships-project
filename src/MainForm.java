import javax.swing.*;
import java.io.IOException;

public class MainForm implements Runnable {

    private final static int GAME_DELAY = 5;
    private final static int SAVE_STATE_DELAY = 30000;

    private JPanel root;
    private Game game;


    public MainForm() {


    }

    private void createUIComponents() {
        game = new Game();
        root = new RenderPanel(game);
        new Thread(this).start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        int timeCounter = 0;

        while (true) {
            try {

                if (timeCounter == 0) {
                    try {
                        Json.appendToFile("GameState.txt", game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                game.update(1);
                root.invalidate();
                root.repaint();

                timeCounter += GAME_DELAY;

                if (timeCounter >= SAVE_STATE_DELAY) {
                    timeCounter = 0;
                }


                Thread.sleep(GAME_DELAY);
            } catch (InterruptedException e) {

            }
        }
    }
}
