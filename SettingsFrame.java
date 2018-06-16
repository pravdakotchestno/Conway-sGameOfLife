import javax.swing.*;

public class SettingsFrame extends JFrame {
    JToggleButton isHaveBoundstoggleButton;
    JButton sumbitButton;
    JSlider fieldSizeSlider;
    JSlider delaySlider;
    public SettingsFrame(){


    }
    public Settings getSettings(){
        return new Settings();
    }
    public void sumbitSettings(){
        GameLogic.start();
        GameLogic.getGameFrame().setEnabled(true);
        this.destroy();
    }
    private void destroy(){
        setVisible(false);
        dispose();
    }
}
