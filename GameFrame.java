import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener {
    private GamePanel gpanel;
    private JButton onestepButton;
    private JToggleButton changeisrunningToggleButton;
    private JButton opensettingsButton;

    private static final String running="Running";
    private static final String notrunning="NotRunning";

    private static final String onestep="One step";

    private static final String settings="Settings";

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

    private ActionListener settingsButtonListener= e -> {
        GameLogic.showSettingsWindow();
    };

    public GameFrame(Settings st,Field paintedfield){

        gpanel=new GamePanel(paintedfield);
        gpanel.setBounds(0,0,st.getSize()*GameLogic.CELLSIZEINPIXELS,st.getSize()*GameLogic.CELLSIZEINPIXELS);

        onestepButton=new JButton(onestep);
        onestepButton.setBounds(GameLogic.CELLSIZEINPIXELS,st.getSize()*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        onestepButton.addActionListener(onestepButtonListener);

        changeisrunningToggleButton=new JToggleButton(notrunning);
        changeisrunningToggleButton.setBounds(GameLogic.CELLSIZEINPIXELS*11,st.getSize()*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        changeisrunningToggleButton.addActionListener(toggleButtonListener);

        opensettingsButton=new JButton(settings);
        opensettingsButton.setBounds(GameLogic.CELLSIZEINPIXELS*22,st.getSize()*GameLogic.CELLSIZEINPIXELS+10,GameLogic.CELLSIZEINPIXELS*10,GameLogic.CELLSIZEINPIXELS*2);
        opensettingsButton.addActionListener(settingsButtonListener);

        setLayout(null);



        add(onestepButton);
        add(changeisrunningToggleButton);
        add(gpanel);
        add(opensettingsButton);
        addMouseListener(this);

        setSize(st.getSize()*GameLogic.CELLSIZEINPIXELS,st.getSize()*GameLogic.CELLSIZEINPIXELS+100);
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
