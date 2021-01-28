package GRAPHICS;

import LOGIC.GameLogic;
import LOGIC.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Render {
    public static void renderGameScreen(Graphics G, int H, int W, String[] map){
        for(int y=0;y< H;++y)
            for(int x=0;x<W;++x)
                switch(map[y].charAt(x)){
                    case 'W':{G.drawImage(ResourcesManager.get("wall"),x*Window.RES,y*Window.RES,Window.RES,Window.RES,null);break;}
                    case 'f':{G.drawImage(ResourcesManager.get("floor"),x*Window.RES,y*Window.RES,Window.RES,Window.RES,null);break;}
                    case 'X':{G.drawImage(ResourcesManager.get("floor"),x*Window.RES,y*Window.RES,Window.RES,Window.RES,null);G.drawImage(ResourcesManager.get("stairs"),x*Window.RES,y*Window.RES,Window.RES,Window.RES,null);break;}
                    case 'D':{G.drawImage(ResourcesManager.get("walldark"),x*Window.RES,y*Window.RES,Window.RES,Window.RES,null);break;}
                }

    }

    public static void renderEntity(Graphics G, String name){
        entity tempPlayer=GameLogic.getEntity(name);
        G.drawImage(ResourcesManager.get(name), tempPlayer.getX()*Window.RES, tempPlayer.getY()*Window.RES, Window.RES, Window.RES, null);
    }

    public static void renderChests(Graphics G, int[][] chestMap, int limit){
        for(int i=0;i<limit;++i){
            G.drawImage(ResourcesManager.get("chest"),chestMap[i][0]*Window.RES,chestMap[i][1]*Window.RES,Window.RES,Window.RES,null);
        }
    }
}
