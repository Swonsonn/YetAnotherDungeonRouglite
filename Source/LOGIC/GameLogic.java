package LOGIC;

import javax.swing.Timer;

public class GameLogic {
    public static entity PLAYER;
    private static Timer timer;
    public static String[] referenceMap;
    public static int Height;
    public static int Width;

    public static void initialise(){
        PLAYER=new entity("player",0,0);
        timer = new Timer(20,new Loops());
        timer.start();
        System.out.println("[Logic]Initialised");

        Height=14;
        Width=14;
    }

    public static void addReferenceMap(String[] map){GameLogic.referenceMap=map;}

    public static void move(int dx, int dy){
        PLAYER.setPos(dx,dy);
    }

    public static entity player(){return PLAYER;}
}
