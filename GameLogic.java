public class GameLogic {
    public static final int MINSIZE = 40;
    public static final int MAXSIZE = calculateMaxSize();
    public static final int MINDELAY = 100;
    public static final int MAXDELAY = 10000;
    public static final int CELLSIZEINPIXELS = 10;

    private static MainGameThread gameThread;
    private static GameFrame gframe;
    private static Settings st;
    private static Field field;

    public static void init(){

        st=new Settings();
        try {
            field = new Field(st.getSize());
        }catch(Exception e){}

        gframe=new GameFrame(st,field);
        gameThread=new MainGameThread();
        gameThread.start();
    }

    public static void stop(){
        gameThread.setRunning(false);
    }
    public static void start(){
        gameThread.setRunning(true);
    }

    public static void tick(){
        field.doMove(st.isHaveBounds());
        gframe.repaintToNewField(field);
    }

    public static void setupNewSettings(){
        stop();
        gframe.setEnabled(false);
        SettingsFrame stf=new SettingsFrame();
    }

    public static Settings getCurrentSettings(){
        return st;
    }

    public static GameFrame getGameFrame(){
        return gframe;
    }

    public static Field getField(){
        return field;
    }

    public static int calculateMaxSize(){
        return 75;
        //TODO
    }
}
