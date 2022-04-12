import javax.swing.*;
import java.awt.*;

public class GameGUI {
    private final int WIDTH;
    private final int HEIGHT;
    private JFrame window;

    public GameGUI(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        // create new window
        window = new JFrame("Minesweeper");

        // make sure window closes automatically
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set size & position of the game window
        int xpos = 0;
        int ypos = 0;
        window.setBounds(xpos, ypos, width, height+50); // +50 to allow for the title bar

        // add components here
        //window.getContentPane().add(this);

        // set visibility
        window.setVisible(true);
    }

    public void render(Minefield m) {
        // manual draw routine
        window.repaint();
    }

    public void paint(Graphics g) {
        // do we need this?
    }
}

