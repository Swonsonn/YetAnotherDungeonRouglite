package LOGIC;

import javax.swing.Timer;

public class GameLogic {
    public static entity PLAYER;
    private static Timer timer;

    public static void initialise(){
        PLAYER=new entity("player",0,0);
        timer = new Timer(15,new Loops());
        timer.start();
        System.out.println("[Logic]Initialised");
    }

    public static void move(int dx, int dy){
        System.out.println("Order to move by "+dx+" "+dy);
        PLAYER.setPos(dx,dy);
    }

    public static entity player(){return PLAYER;}
}
