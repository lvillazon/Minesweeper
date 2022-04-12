import javax.swing.*;
import java.awt.*;

public class DrawPane extends JPanel {
    private final int MARGIN;

    public DrawPane() {
        super();
        MARGIN = 20;
    }



    public void paintComponent(Graphics g) {
        g.drawRect(MARGIN,MARGIN,getWidth()-2*MARGIN,getHeight()-2*MARGIN);
    }
}
