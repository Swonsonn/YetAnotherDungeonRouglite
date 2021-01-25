package LOGIC;

public class entity {
    private static String name;
    private static int X;
    private static int Y;

    public entity(String name,int X,int Y){
        entity.name =name;
        entity.X=X;
        entity.Y=Y;
    }

    public void setPos(int x, int y){
        X = X + x;
        Y = Y + y;
        System.out.println("X:"+X+" Y:"+Y);
    }

    public int getX(){return X;}

    public int getY(){return Y;}

    public String getName(){return name;}
}
