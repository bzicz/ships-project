import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RenderPanel extends JPanel {
    private Game game;




    public RenderPanel(Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        try {
            game.render(g);
        }
        catch (ConcurrentModificationException e) {}

    }
}
