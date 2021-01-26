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
                    case 'f':{G.setColor(Color.LIGHT_GRAY);G.fillRect(x*Window.RES,y*Window.RES,Window.RES,Window.RES);break;}
                    case 'X':{G.setColor(Color.RED);G.fillRect(x*Window.RES,y*Window.RES,Window.RES,Window.RES);break;}
                }

    }

    public static void renderPlayer(Graphics G){
        entity tempPlayer=GameLogic.player();
        G.drawImage(ResourcesManager.get("player"), tempPlayer.getX()*Window.RES, tempPlayer.getY()*Window.RES, Window.RES, Window.RES, null);
    }
}