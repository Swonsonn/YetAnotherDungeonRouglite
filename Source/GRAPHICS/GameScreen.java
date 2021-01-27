package GRAPHICS;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.Graphics;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    private int UpperChestLimit=0;
    private int[][] Chests;

    public GameScreen(){
        super();
        System.out.println("######################################");
        System.out.println("[GameScreen]Start initialising");
        this.setFocusable(true);
        this.addKeyListener(new KB());
        GameLogic.initialise();
        MapGenerator.initialise(GameLogic.Width, GameLogic.Height, System.currentTimeMillis());
        GameLogic.addReferenceMap(MapGenerator.getMAP());
        GameLogic.move("player",MapGenerator.getEnterX(), MapGenerator.getEnterY());
        ResourcesManager.loadRes();
        Chests=MapGenerator.getChests();
        while(Chests[UpperChestLimit][0]!=-1){
            UpperChestLimit++;
        }
        System.out.println("[GameScreen]End initialising");
        System.out.println("######################################\n\n\n");
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Render.renderGameScreen(graphics,MapGenerator.getHeight(),MapGenerator.getWidth(),MapGenerator.getMAP());
        Render.renderChests(graphics, Chests, UpperChestLimit);
        Render.renderEntity(graphics, "player");
        repaint();
        }

    }

