package LOGIC;

import INFOREADERS.FontManager;
import INFOREADERS.ItemList;
import INFOREADERS.ResourcesManager;
import GRAPHICS.Window;
import INPUTREADERS.Loops;
import MAPRELATEDMODULES.MapGenerator;
import STRUCTURES.Chest;
import STRUCTURES.entity;
import STRUCTURES.item;

import javax.swing.Timer;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

public class GameLogic {
    private static HashMap<String, entity> Entity;
    private static Timer timer;
    private static String[] referenceMap;
    private static Chest[] Chests;

    public static boolean ChestIsNearBy;
    public static int numOfChest;

    private static int Height;
    private static int Width;

    public static void checkChests(){
        ChestIsNearBy=false;
        numOfChest=-1;
        entity player=getEntity("player");
        for(int i=0;i<Chests.length;++i){
            if(player.getX()==Chests[i].X && player.getY()==Chests[i].Y){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()-1==Chests[i].X && player.getY()==Chests[i].Y){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()+1==Chests[i].X && player.getY()==Chests[i].Y){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()==Chests[i].X && player.getY()-1==Chests[i].Y){
                ChestIsNearBy = true;
                numOfChest=i;
            }
            if(player.getX()==Chests[i].X && player.getY()+1==Chests[i].Y){
                ChestIsNearBy = true;
                numOfChest=i;
            }
        }
    }

    public static void openChest(){
        Chests[numOfChest].IsOpen=true;
    }

    public static void initialise(int H, int W) throws FileNotFoundException {
        System.out.println("############################");
        System.out.println("[Logic]Initialising");

        Height=H;
        Width=W;
        Window.PLAYERPOSY=Window.HEIGHT/2;
        Window.PLAYERPOSX=(int)(Window.WIDTH*0.43);

        Entity=new HashMap<String, entity>();
        System.out.println("[Logic]Generating map");
        MapGenerator.initialise(GameLogic.Width, GameLogic.Height, System.currentTimeMillis());
        System.out.println("[Logic]Map generated");
        System.out.println("[Logic]Loading resources");
        ResourcesManager.loadRes();
        FontManager.loadRes();
        ItemList.loadRes();
        System.out.println("[Logic]Resources loaded");
        Entity.put("player", new entity("player",MapGenerator.getEnterX(), MapGenerator.getEnterY()));
        referenceMap=MapGenerator.getMAP();

        Chests=MapGenerator.getChests();//refactor this with new class
        fillChests();

        System.out.println("[Logic]"+Chests.length+" chests placed");
        timer = new Timer(20,new Loops());
        timer.start();
        System.out.println("[Logic]Initialised");
        System.out.println("############################\n");
    }

    private static void fillChests(){
        Random rand=new Random();
        for(int I=0;I<Chests.length;++I){
            int r= rand.nextInt(ItemList.NUMOFITEMS)+1;
            item temp=ItemList.get(r);
            System.out.println(temp.getName());
            Chests[I].inv.add(temp,0,0);
        }
    }

    public static String[] getMAP(){return referenceMap;}

    public static Chest[] getCHEST(){return Chests;}

    public static Chest getSpecificChest(int num){return Chests[num];}

    public static int getUPPERLIMIT(){return Chests.length;}

    public static void move(String name, int dx, int dy){
        Entity.get(name).setPos(dx,dy);
    }

    public static entity getEntity(String name){return Entity.get(name);}
}
