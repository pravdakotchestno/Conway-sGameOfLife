public class MainGameThread extends Thread{
    private boolean isRunning=false;

    public void run() {
        super.run();
        while(true){
            delay(GameLogic.getDelay()/2);
            if(isRunning){
                delay(GameLogic.getDelay()/2);
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
