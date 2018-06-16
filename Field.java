import java.awt.*;

public class Field {
    private int size;
    private boolean[][] field;
    private static final int[] CHECKS={-1, -1,   -1, 0,   -1, 1,   0, 1,   1, 1,   1, 0,   1, -1,   0, -1};

    public Field(int size) throws Exception{


        if(size<GameLogic.MINSIZE||size>GameLogic.MAXSIZE){
            throw new Exception();
        }else{
            this.size=size;
            field=new boolean[size][size];
        }
    }

    public void doMove(boolean isHaveBounds){

        boolean[][] newfield= new boolean[size][size];
        for(int y=0;y<size;y++)for(int x=0;x<size;x++){

            switch(getTrueCellsAround(x,y,isHaveBounds,field)){
                case 2: {
                    setCell(x,y,getCell(x,y,field),newfield);
                    break;
                }
                case 3: {
                    setCell(x,y,true, newfield);

                    break;
                }
                default: {
                    setCell(x,y,false, newfield);

                    break;
                }
            }

        }

        field=newfield;

    }
    private int getTrueCellsAround(int x, int y, boolean isHaveBounds, boolean[][] field){

        int trueCellsAround=0;

        for(int k=0;k<8;k++){
            if(isHaveBounds) {
                if (x + CHECKS[k * 2 + 1] >= 0 && y + CHECKS[k * 2] >= 0 && x + CHECKS[k * 2 + 1] < this.size && y + CHECKS[k * 2] < this.size) {
                    if (getCell(x + CHECKS[k * 2 + 1], y + CHECKS[k * 2], field)) trueCellsAround++;
                }
            }else{
                if (getCell(rem((x + CHECKS[k * 2 + 1]),size), rem((y + CHECKS[k * 2]),size), field)) trueCellsAround++;
            }

        }
        return trueCellsAround;
    }

    public void changeCell(int x,int y,boolean[][] field){
        if(x<this.size&&y<this.size&&x>=0&&y>=0){
            field[y][x]=!this.field[y][x];
        }
    }
    public void changeCell(int x,int y){
        changeCell(x,y,this.field);
    }

    public void setCell(int x,int y, boolean value,boolean[][] field){
        if(x<this.size&&y<this.size&&x>=0&&y>=0){
            field[y][x]=value;
        }
    }

    public boolean getCell(int x,int y,boolean[][] field){
        return field[y][x];
    }

    public void paint(Graphics gr){

        int x=0;
        int y=0;

        for(boolean[] array:field){

            for(boolean cell:array){

                if(cell) {
                    gr.setColor(Color.cyan);
                }else{
                    gr.setColor(Color.BLACK);
                }
                gr.fillRect(x,y,GameLogic.CELLSIZEINPIXELS,GameLogic.CELLSIZEINPIXELS);

                x+=GameLogic.CELLSIZEINPIXELS;
            }

            x=0;
            y+=GameLogic.CELLSIZEINPIXELS;

        }
    }
    private int rem(int num,int div){
        if(num>=0){
            return num%div;
        }else{
            return num+div;
        }
    }
}