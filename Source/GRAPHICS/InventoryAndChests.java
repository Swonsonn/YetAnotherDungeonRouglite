package GRAPHICS;

import javax.swing.*;
import java.awt.*;

public class InventoryAndChests extends JPanel {
    public InventoryAndChests(){
        super();
        this.setBounds(100,100, Window.PLAYERPOSX*2-100, Window.PLAYERPOSY*2-200);
        this.setFocusable(false);
        this.setVisible(false);
        this.setBackground(Color.lightGray);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Render.renderINVchest(graphics);
    }
}
