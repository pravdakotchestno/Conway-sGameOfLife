public class GameLogic {

    public static final int SIZE = calculateSize();
    public static final int CELLSIZEINPIXELS = 10;

    private static MainGameThread gameThread;
    private static GameFrame gframe;
    private static int delay;
    private static Field field;

    public static void init(){

        delay=100;
        try {
            field = new Field(SIZE);
        }catch(Exception e){}

        gframe=new GameFrame(field);
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
        field.doMove();
        gframe.repaintToNewField(field);
    }
    public static int getDelay(){
        return delay;
    }

    public static GameFrame getGameFrame(){
        return gframe;
    }

    public static Field getField(){
        return field;
    }

    public static int calculateSize(){
        return 65;
        //TODO
    }
    public static void setupNewDelay(int del){
        delay=del;
    }
}
