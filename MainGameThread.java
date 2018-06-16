public class MainGameThread extends Thread{
    private boolean isRunning=false;

    public boolean isRunning() {
        return isRunning;
    }

    public void run() {
        super.run();
        while(true){
            delay(GameLogic.getCurrentSettings().getDelay());
            if(isRunning){
                delay(GameLogic.getCurrentSettings().getDelay());
                GameLogic.tick();
            }

        }
    }
    public void setRunning(boolean isRunning){
        this.isRunning=isRunning;
    }

    private static void delay(int delay){
        try{Thread.sleep(delay);}catch(Exception e){}
    }
}
