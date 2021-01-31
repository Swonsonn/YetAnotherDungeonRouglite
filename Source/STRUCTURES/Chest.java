package STRUCTURES;

public class Chest {
    public boolean IsOpen;
    public int X;
    public int Y;
    public container inv;

    public Chest(int X, int Y){
        this.X=X;
        this.Y=Y;
        IsOpen=false;
        inv=new container(4,8);
    }
}
