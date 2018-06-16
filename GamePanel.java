import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    private Field paintedfield;
    public GamePanel(Field paintedfield){
        this.paintedfield=paintedfield;

    }
    public void repaintToNewField(Field field){
        this.paintedfield=field;
        repaint();
    }
    public void paintComponent(Graphics gr){
        paintedfield.paint(gr);
    }
}
