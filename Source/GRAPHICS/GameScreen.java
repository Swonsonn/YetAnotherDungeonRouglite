package GRAPHICS;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import LOGIC.GameLogic;
import MAPRELATEDMODULES.MapGenerator;

public class GameScreen extends JPanel{
    private JToggleButton INV;
    private InventoryAndChests InventoryPanel;
    public static boolean INVActive;

    public GameScreen() throws FileNotFoundException {
        super();
        this.setFocusable(true);
        GameLogic.initialise(22,22);
        this.setLayout(null);

        ImageIcon ico = new ImageIcon("Resources/Textures/inv.png");
        INV=new JToggleButton();
        INV.setBounds(Window.PLAYERPOSX*2+10,330,180,80);
        INV.setIcon(ico);
        INV.setFocusable(false);

        InventoryPanel=new InventoryAndChests();

        this.add(INV);
        this.add(InventoryPanel);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Render.readPlayer();
        Render.renderBackground(graphics);
        Render.renderGameScreen(graphics, MapGenerator.getHeight(), MapGenerator.getWidth(), MapGenerator.getMAP());
        Render.renderChests(graphics, GameLogic.getCHEST(), GameLogic.getUPPERLIMIT());
        Render.renderEntity(graphics, "player");
        GameLogic.checkChests();
        if(GameLogic.ChestIsNearBy)
            Render.chestOpenMessage(graphics);
        //Render.renderBackgroundStats(graphics);
        Render.renderStatsText(graphics);
        if(INV.isSelected()){
            INVActive=true;
            InventoryPanel.setVisible(true);
            InventoryPanel.repaint();
        } else{
            INVActive=false;
            InventoryPanel.setVisible(false);
        }
        repaint();
        }

    }

