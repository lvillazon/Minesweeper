import javax.swing.*;
import java.awt.*;

public class GameGUI extends JComponent {
    private final int WIDTH;
    private final int HEIGHT;
    private final int MARGIN;
    private final int SQUARE_SIZE;

    private JFrame window;
    private GameMap map;

    public GameGUI(int width, int height, GameMap map) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MARGIN = 20;
        this.map = map;
        SQUARE_SIZE = (HEIGHT-2*MARGIN) / map.rows();

        // create new window
        window = new JFrame("Minesweeper");

        // make sure window closes automatically
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set size & position of the game window
        int xpos = 0;
        int ypos = 0;
        window.setBounds(xpos, ypos, width, height+50); // +50 to allow for the title bar

        // add this component to the actual JFrame
        window.getContentPane().add(this);

        // set visibility
        window.setVisible(true);
    }

    public void render() {
        // manual draw routine
        window.repaint();
    }

    public void paintComponent(Graphics g) {
        // draw the actual map of mines
        for (int i=0; i<map.cols(); i++) {
            for (int j=0; j<map.rows(); j++) {
                int x = MARGIN + i * SQUARE_SIZE;
                int y = MARGIN + j * SQUARE_SIZE;
                int contents = map.getSquare(i, j);
                if (contents == 9) { // unexplored
                    g.setColor(Color.gray);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                }
                g.setColor(Color.black);
                g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                if (contents > 0 && contents <9) {
                    g.drawString(Integer.toString(contents),
                            x + SQUARE_SIZE / 2 - g.getFontMetrics().stringWidth("X") / 2,
                            y + SQUARE_SIZE / 2 + g.getFontMetrics().getHeight() / 2);
                }
            }
        }
    }
}

