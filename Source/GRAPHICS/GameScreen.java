package GRAPHICS;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.Graphics;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{

    public GameScreen(){
        super();
        this.setFocusable(true);
        this.addKeyListener(new KB());
        GameLogic.initialise();
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Render.renderGameScreen(graphics,MapGenerator.getHeight(),MapGenerator.getWidth(),MapGenerator.getMAP());
        Render.renderChests(graphics, GameLogic.getCHEST(), GameLogic.getUPPERLIMIT());
        Render.renderEntity(graphics, "player");
        repaint();
        }

    }

