import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener {
    private GamePanel gpanel;
    private JButton onestepButton;
    private JToggleButton changeisrunningToggleButton;
    

    private static final String running="Running";
    private static final String notrunning="NotRunning";

    private static final String onestep="One step";

    private ActionListener onestepButtonListener= e -> {
        if (!changeisrunningToggleButton.isSelected()) {
            GameLogic.tick();
        }
    };
    private ActionListener toggleButtonListener= e -> {

        if (changeisrunningToggleButton.isSelected()) {
            changeisrunningToggleButton.setText(running);
            GameLogic.start();
        }else{
            changeisrunningToggleButton.setText(notrunning);
            GameLogic.stop();
        }

    };

    public GameFrame(Field paintedfield){

        gpanel=new GamePanel(paintedfield);
        gpanel.setBounds(0,0,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS);

        onestepButton=new JButton(onestep);
        onestepButton.setBounds(GameLogic.CELLSIZEINPIXELS,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        onestepButton.addActionListener(onestepButtonListener);

        changeisrunningToggleButton=new JToggleButton(notrunning);
        changeisrunningToggleButton.setBounds(GameLogic.CELLSIZEINPIXELS*11,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        changeisrunningToggleButton.addActionListener(toggleButtonListener);

        setLayout(null);

        add(onestepButton);
        add(changeisrunningToggleButton);
        add(gpanel);
        addMouseListener(this);

        setSize(GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS+100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);



    }
    public void repaintToNewField(Field field){
        gpanel.repaintToNewField(field);
    }

    public void destroy(){
        setVisible(false);
        dispose();
    }



    public void mouseClicked(MouseEvent e) {
        GameLogic.getField().changeCell(e.getX()/GameLogic.CELLSIZEINPIXELS,(e.getY()-23)/GameLogic.CELLSIZEINPIXELS);
        GameLogic.getGameFrame().repaint();
    }
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
