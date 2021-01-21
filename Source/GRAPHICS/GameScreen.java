package GRAPHICS;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    private MapGenerator mapgen;
    private static ResourcesManager res;

    public GameScreen(){
        super();
        res=new ResourcesManager();
        this.setFocusable(true);
        mapgen=new MapGenerator(14,14);
        this.addKeyListener(new KB());
    }

    public MapGenerator getMapgenCopy(){
        return this.mapgen;
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,Window.WIDTH, Window.HEIGHT);
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
        GameLogic logic = new GameLogic(graphics);
        logic.initialise(mapgen.getX(), mapgen.getY());

        }
    }

