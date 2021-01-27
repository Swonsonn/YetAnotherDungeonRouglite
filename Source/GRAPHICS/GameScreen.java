package GRAPHICS;

import javax.swing.*;
import java.awt.Graphics;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    public GameScreen(){
        super();
        System.out.println("######################################");
        System.out.println("[GameScreen]Start initialising");
        this.setFocusable(true);
        this.addKeyListener(new KB());
        GameLogic.initialise();
        MapGenerator.initialise(GameLogic.Width, GameLogic.Height, System.currentTimeMillis());
        GameLogic.addReferenceMap(MapGenerator.getMAP());
        GameLogic.move(MapGenerator.getEnterX(), MapGenerator.getEnterY());
        ResourcesManager.loadRes();
        System.out.println("[GameScreen]End initialising");
        System.out.println("######################################\n\n\n");
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Render.renderGameScreen(graphics,MapGenerator.getHeight(),MapGenerator.getWidth(),MapGenerator.getMAP());
        Render.renderPlayer(graphics);
        repaint();
        }

    }

