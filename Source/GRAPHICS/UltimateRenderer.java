package GRAPHICS;

import LOGIC.entity;
import MAPRELATEDMODULES.MapGenerator;

import java.awt.*;

public class UltimateRenderer {
    ResourcesManager res;

    public UltimateRenderer(){res=new ResourcesManager();}

    public void drawLevel(Graphics graphics, MapGenerator mapgen){
        String[] map=mapgen.Generate();
        for(int y=0;y<mapgen.SizeH;++y) {
            for (int x = 0; x < mapgen.SizeW; ++x) {
                switch (map[x].charAt(y)) {
                    case 'W': {
                        graphics.drawImage(res.get("wall"),x* Window.RES,y* Window.RES,Window.RES,Window.RES,null);
                        break;
                    }
                    case 'f': {
                        graphics.setColor(Color.LIGHT_GRAY);
                        graphics.fillRect(x * Window.RES, y * Window.RES, Window.RES, Window.RES);
                        break;
                    }
                    case 'X': {
                        graphics.setColor(Color.RED);
                        graphics.fillRect(x * Window.RES, y * Window.RES, Window.RES, Window.RES);
                        break;
                    }
                }

            }
        }
    }

    public void drawEntity(Graphics graphics, entity e){
        graphics.drawImage(res.get(e.getName()),e.getX()* Window.RES,e.getY()* Window.RES,Window.RES,Window.RES,null);
    }
}
