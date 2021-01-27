package LOGIC;

import javax.swing.Timer;
import java.util.HashMap;

public class GameLogic {
    private static HashMap<String, entity> Entity;
    private static Timer timer;
    public static String[] referenceMap;
    public static int Height;
    public static int Width;

    public static void initialise(){
        Entity=new HashMap<String, entity>();
        Entity.put("player", new entity("player",0,0));
        timer = new Timer(20,new Loops());
        timer.start();

        Height=14;
        Width=14;
        System.out.println("[Logic]Initialised");
    }

    public static void addReferenceMap(String[] map){GameLogic.referenceMap=map;}

    public static void move(String name, int dx, int dy){
        Entity.get(name).setPos(dx,dy);
    }

    public static entity getEntity(String name){return Entity.get(name);}
}
