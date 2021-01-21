package LOGIC;

import GRAPHICS.ResourcesManager;
import GRAPHICS.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class entity {
    private static String name;
    private static int X;
    private static int Y;

    public entity(String name,int X,int Y){
        entity.name =name;
        entity.X =X;
        entity.Y =Y;
    }

    public void render(Graphics graphics){
        BufferedImage texture = ResourcesManager.get(name);
        graphics.drawImage(texture,X* GRAPHICS.Window.RES,Y* GRAPHICS.Window.RES, GRAPHICS.Window.RES, Window.RES,null);
    }

    public void setPos(int x, int y){
        X+=x;
        Y+=y;
        System.out.println("X:"+X+" Y:"+Y);
    }

    public int getX(){return X;}

    public int getY(){return Y;}

    public String getName(){return name;}
}
