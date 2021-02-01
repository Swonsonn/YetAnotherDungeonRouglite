package STRUCTURES;

import LOGIC.GameLogic;

public class entity {
    private static String name;
    private static int X;
    private static int Y;

    public entity(String name,int X,int Y){
        entity.name =name;
        entity.X=X;
        entity.Y=Y;
        System.out.println("[Entity]"+name+ " created X:"+X+" Y:"+Y);
    }

    public void setPos(int x, int y){
        if(GameLogic.getMAP()[Y+y].charAt(X+x)!='W'){
            X = X + x;
            Y = Y + y;
            //System.out.println("[Entity]"+name+" moved to X:"+X+" Y:"+Y);
        }
    }

    public int getX(){return X;}
    public int getY(){return Y;}
}
