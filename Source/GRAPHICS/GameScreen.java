package GRAPHICS;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    MapGenerator mapgen;

    public GameScreen(){
        super();
        this.setFocusable(true);
        this.addKeyListener(new KB());
        this.mapgen=new MapGenerator(14,14);

        GameLogic.initialise();
        GameLogic.move(mapgen.X, mapgen.Y);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        //remake gui
        }

    }

