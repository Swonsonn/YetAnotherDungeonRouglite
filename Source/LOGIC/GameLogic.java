package LOGIC;

import GRAPHICS.FontManager;
import GRAPHICS.ResourcesManager;
import GRAPHICS.Window;
import MAPRELATEDMODULES.MapGenerator;

import javax.swing.Timer;
import java.util.HashMap;

public class GameLogic {
    private static HashMap<String, entity> Entity;
    private static Timer timer;
    private static String[] referenceMap;
    private static int[][] Chests;
    private static int UpperChestLimit;
    public static boolean ChestIsNearBy;

    public static int numOfChest;

    private static int Height;
    private static int Width;

    public static void checkChests(){
        ChestIsNearBy=false;
        numOfChest=-1;
        entity player=getEntity("player");
        for(int i=0;i<UpperChestLimit;++i){
            if(player.getX()==Chests[i][0] && player.getY()==Chests[i][1]){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()-1==Chests[i][0] && player.getY()==Chests[i][1]){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()+1==Chests[i][0] && player.getY()==Chests[i][1]){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()==Chests[i][0] && player.getY()-1==Chests[i][1]){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()==Chests[i][0] && player.getY()+1==Chests[i][1]){
                ChestIsNearBy = true;
                numOfChest=i;
            }
        }
    }

    public static void openChest(){
        Chests[numOfChest][2]=0;
    }

    public static void initialise(){
        System.out.println("############################");
        System.out.println("[Logic]Initialising");

        Height=14;
        Width=14;
        Window.PLAYERPOSY=Window.HEIGHT/2;
        Window.PLAYERPOSX=(int)(Window.WIDTH*0.43);

        UpperChestLimit=0;
        Entity=new HashMap<String, entity>();
        System.out.println("[Logic]Generating map");
        MapGenerator.initialise(GameLogic.Width, GameLogic.Height, System.currentTimeMillis());
        System.out.println("[Logic]Map generated");
        System.out.println("[Logic]Loading resources");
        ResourcesManager.loadRes();
        FontManager.loadRes();
        System.out.println("[Logic]Resources loaded");
        Entity.put("player", new entity("player",MapGenerator.getEnterX(), MapGenerator.getEnterY()));
        referenceMap=MapGenerator.getMAP();
        Chests=MapGenerator.getChests();
        while(Chests[UpperChestLimit][0]!=-1){UpperChestLimit++;}
        timer = new Timer(20,new Loops());
        timer.start();
        System.out.println("[Logic]Initialised");
        System.out.println("############################\n");
    }

    public static String[] getMAP(){return referenceMap;}

    public static int[][] getCHEST(){return Chests;}

    public static int getUPPERLIMIT(){return UpperChestLimit;}

    public static void move(String name, int dx, int dy){
        Entity.get(name).setPos(dx,dy);
    }

    public static entity getEntity(String name){return Entity.get(name);}
}
