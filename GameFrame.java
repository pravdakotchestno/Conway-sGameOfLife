import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener {

    private static final String running="Running";
    private static final String notrunning="NotRunning";
    private static final String sumbit="Sumbit";
    private static final String onestep="One step";

    private GamePanel gpanel;
    private JButton onestepButton;
    private JToggleButton changeisrunningToggleButton;
    private JTextField delayfield;
    private JButton sumbitButton;

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
    private ActionListener sumbitButtonListener= e -> {

        try {
            GameLogic.setNewDelay(Integer.parseInt(delayfield.getText()));
        }catch(Exception ex){}

    };

    public GameFrame(Field paintedfield){

        gpanel=new GamePanel(paintedfield);
        gpanel.setBounds(0,0,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS);

        delayfield=new JTextField(GameLogic.getDelay());
        delayfield=new JTextField(GameLogic.getDelay()+"");
        delayfield.setBounds(GameLogic.CELLSIZEINPIXELS,(GameLogic.SIZE+3)*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);


        onestepButton=new JButton(onestep);
        onestepButton.setBounds(GameLogic.CELLSIZEINPIXELS,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        onestepButton.addActionListener(onestepButtonListener);

        changeisrunningToggleButton=new JToggleButton(notrunning);
        changeisrunningToggleButton.setBounds(GameLogic.CELLSIZEINPIXELS*11,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        changeisrunningToggleButton.addActionListener(toggleButtonListener);

        sumbitButton=new JButton(sumbit);
        sumbitButton.setBounds(GameLogic.CELLSIZEINPIXELS*11,(GameLogic.SIZE+3)*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        sumbitButton.addActionListener(sumbitButtonListener);

        add(onestepButton);
        add(changeisrunningToggleButton);
        add(gpanel);
        add(sumbitButton);
        add(delayfield);
        addMouseListener(this);

        setLayout(null);
        setSize(GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS,GameLogic.SIZE*GameLogic.CELLSIZEINPIXELS+100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void repaintToNewField(Field field){
        gpanel.repaintToNewField(field);
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
