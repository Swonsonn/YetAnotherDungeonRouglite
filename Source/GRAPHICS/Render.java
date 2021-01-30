package GRAPHICS;

import INFORMATION.PlayerInformation;
import LOGIC.GameLogic;
import LOGIC.entity;

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
                    case 'D':{G.drawImage(ResourcesManager.get("walldark"),getX(x),getY(y),Window.RES,Window.RES,null);break;}
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

    public static void renderChests(Graphics G, int[][] chestMap, int limit){
        for(int i=0;i<limit;++i){
            G.drawImage(ResourcesManager.get("chest"),getX(chestMap[i][0]),getY(chestMap[i][1]),Window.RES,Window.RES,null);
        }
    }
}
