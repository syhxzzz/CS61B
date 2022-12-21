package byog.Core;

public class position {
    int x;
    int y;
    public  position(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int setXscale(){
        return x;
    }
    public int setYscale(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
}
