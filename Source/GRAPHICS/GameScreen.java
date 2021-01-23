package GRAPHICS;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    MapGenerator mapgen;
    UltimateRenderer render;

    public GameScreen(){
        super();
        this.setFocusable(true);
        this.addKeyListener(new KB());
        this.mapgen=new MapGenerator(14,14);
        this.render=new UltimateRenderer();

        GameLogic.initialise();
        GameLogic.move(mapgen.X, mapgen.Y);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        render.drawLevel(graphics, mapgen);
        render.drawEntity(graphics, GameLogic.player());
        }

    }

