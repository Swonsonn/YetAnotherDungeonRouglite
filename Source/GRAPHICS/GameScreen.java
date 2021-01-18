package GRAPHICS;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    MapGenerator mapgen;

    public GameScreen(){
        super();
        this.setFocusable(true);
        mapgen=new MapGenerator(14,14);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,Window.WIDTH, Window.HEIGHT);
        String[] map=mapgen.Generate();

        for(int y=0;y<mapgen.SizeH;++y) {
            for (int x = 0; x < mapgen.SizeW; ++x) {
                switch (map[y].charAt(x)) {
                    case 'W': {
                        graphics.setColor(Color.DARK_GRAY);
                        break;
                    }
                    case 'O': {
                        graphics.setColor(Color.LIGHT_GRAY);
                        break;
                    }
                    case 'X': {
                        graphics.setColor(Color.RED);
                        break;
                    }
                }
                System.out.print(map[y].charAt(x));
                graphics.fillRect(x * 32, y * 32, 32, 32);
            }
            System.out.println();
        }



        //repaint();
    }
}
