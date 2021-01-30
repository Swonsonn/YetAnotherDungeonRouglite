package GRAPHICS;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;

import LOGIC.GameLogic;
import LOGIC.KB;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    private JToggleButton INV;

    public GameScreen(){
        super();
        this.setFocusable(true);
        GameLogic.initialise();
        this.setLayout(null);
        ImageIcon ico = new ImageIcon("Resources/Textures/wall.png");
        INV=new JToggleButton("INVENTORY");
        INV.setBounds(Window.PLAYERPOSX*2+10,330,180,80);
        INV.setIcon(ico);
        INV.setFocusable(false);
        this.add(INV);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Render.readPlayer();
        Render.renderBackground(graphics);
        Render.renderGameScreen(graphics,MapGenerator.getHeight(),MapGenerator.getWidth(),MapGenerator.getMAP());
        Render.renderChests(graphics, GameLogic.getCHEST(), GameLogic.getUPPERLIMIT());
        Render.renderEntity(graphics, "player");
        //Render.renderBackgroundStats(graphics);
        Render.renderStatsText(graphics);
        repaint();
        }

    }

