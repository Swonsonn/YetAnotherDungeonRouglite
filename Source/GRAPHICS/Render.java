package GRAPHICS;

import INFORMATION.PlayerInformation;
import INFOREADERS.FontManager;
import INFOREADERS.ResourcesManager;
import LOGIC.GameLogic;
import STRUCTURES.Chest;
import STRUCTURES.entity;

import java.awt.*;

public class Render {
    private static int playerX, playerY;

    public static void readPlayer(){
        entity e=GameLogic.getEntity("player");
        playerX=e.getX();
        playerY=e.getY();
    }

    public static void renderGameScreen(Graphics G, int H, int W, String[] map){
        for(int y=0;y< H;++y)
            for(int x=0;x<W;++x)
                switch(map[y].charAt(x)){
                    case 'W':{G.drawImage(ResourcesManager.get("wall"), getX(x),getY(y),Window.RES,Window.RES,null);break;}
                    case 'f':{G.drawImage(ResourcesManager.get("floor"),getX(x),getY(y),Window.RES,Window.RES,null);break;}
                    case 'X':{G.drawImage(ResourcesManager.get("floor"),getX(x),getY(y),Window.RES,Window.RES,null);G.drawImage(ResourcesManager.get("stairs"),getX(x),getY(y),Window.RES,Window.RES,null);break;}
                    case 'D':{G.drawImage(ResourcesManager.get("darkwall"),getX(x),getY(y),Window.RES,Window.RES,null);break;}
                }

    }

    private static int getX(int x){
        int tempX;
        tempX=x*Window.RES+(Window.PLAYERPOSX-playerX*Window.RES);
        return tempX;
    }

    private static int getY(int y){
        int tempY;
        tempY=y*Window.RES+(Window.PLAYERPOSY-playerY*Window.RES);
        return tempY;
    }

    public static void renderEntity(Graphics G, String name){
        entity e=GameLogic.getEntity(name);
        G.drawImage(ResourcesManager.get(name), getX(e.getX()),getY(e.getY()), Window.RES, Window.RES, null);
    }

    public static void renderBackgroundStats(Graphics G){
        G.drawImage(ResourcesManager.get("filler"), Window.PLAYERPOSX*2, 0, Window.WIDTH-(Window.PLAYERPOSX*2), Window.HEIGHT, null);
    }

    public static void renderStatsText(Graphics G){
        int POSx=Window.PLAYERPOSX*2+10,POSy=20;
        G.setFont(FontManager.get("origami"));
        G.drawImage(ResourcesManager.get("wall"), POSx, POSy, 190,190,null);
        POSy+=220;
        G.setColor(Color.GREEN);
        G.drawString("HP  :  "+PlayerInformation.HPcur+"/"+PlayerInformation.HPmax, POSx, POSy);
        POSy+=20;
        G.setColor(Color.BLUE);
        G.drawString("MAN:  "+PlayerInformation.MANcur+"/"+PlayerInformation.MANmax, POSx, POSy);
        POSy+=20;
        G.setColor(Color.CYAN);
        G.drawString("DEF:  "+PlayerInformation.DEFfull, POSx, POSy);
        POSy+=20;
        G.setColor(Color.RED);
        G.drawString("DMG:  "+PlayerInformation.DMGfull, POSx, POSy);
        POSy+=20;
        G.setColor(Color.YELLOW);
        G.drawString("AGL:  ", POSx, POSy);
        POSy+=20;
    }

    public static void renderBackground(Graphics G){
        int x=0,y=0;
        while(y<Window.HEIGHT){
            x=0;
            while(x<Window.WIDTH){
                G.drawImage(ResourcesManager.get("walldark"), x , y,Window.RES,Window.RES,null);
                x+=Window.RES;
            }
            y+=Window.RES;
        }
    }

    public static void renderChests(Graphics G, Chest[] chestMap, int limit){
        for(int i=0;i<limit;++i){
            if(chestMap[i].IsOpen==false){G.drawImage(ResourcesManager.get("chest"),getX(chestMap[i].X),getY(chestMap[i].Y),Window.RES,Window.RES,null);}
            else{G.drawImage(ResourcesManager.get("openchest"),getX(chestMap[i].X),getY(chestMap[i].Y),Window.RES,Window.RES,null);}//closed chest
        }
    }

    public static void renderINVchest(Graphics G){
        if(GameLogic.ChestIsNearBy){
            Chest chest=GameLogic.getSpecificChest(GameLogic.numOfChest);
            for(int y=0;y<4;++y) {
                for (int x = 0; x<8; ++x) {
                    G.setColor(Color.GRAY);
                    G.fillRect(100+x * Window.invRES, 100+y * Window.invRES, Window.invRES, Window.invRES);
                    G.setColor(Color.DARK_GRAY);
                    G.drawRect(100+x * Window.invRES, 100+y * Window.invRES, Window.invRES, Window.invRES);
                    //G.drawImage(ResourcesManager.get(chest.inv.show(x,y)), 100+x * Window.invRES, 100+y * Window.invRES, Window.invRES, Window.invRES, null);
                }
            }
            for(int y=0;y<4;++y) {
                for (int x = 0; x<8; ++x) {
                    if(chest.inv.getPart(x,y)==0){
                        int X,Y;
                        X=chest.inv.X(x,y);
                        Y=chest.inv.Y(x,y);
                        G.drawImage(ResourcesManager.get(chest.inv.take(x,y)), 100+x * Window.invRES, 100+y * Window.invRES, Window.invRES*X, Window.invRES*Y, null);
                    }
                }}
        }
    }

    public static void chestOpenMessage(Graphics G){
        G.setColor(Color.WHITE);
        G.setFont(FontManager.get("aldrich"));
        G.drawString("Press E to open the chest", Window.PLAYERPOSX-4*Window.RES, Window.PLAYERPOSY+2*Window.RES);
    }
}
