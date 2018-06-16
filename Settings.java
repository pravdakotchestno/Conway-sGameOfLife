public class Settings {
    private int delay;
    private int size;
    private boolean isHaveBounds;

    public Settings(int delay,int size,boolean isHaveBounds){
        this.delay=delay;
        this.size=size;
        this.isHaveBounds=isHaveBounds;
    }
    public Settings (){
        this.delay=100;
        this.size=75;
        this.isHaveBounds=false;
    }

    public int getDelay() {
        return this.delay;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isHaveBounds() {
        return this.isHaveBounds;
    }
}
