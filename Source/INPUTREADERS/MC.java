package INPUTREADERS;

import GRAPHICS.InventoryAndChests;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MC extends MouseAdapter {
    public void mousePressed(MouseEvent e){
        InventoryAndChests.grabX=e.getX();
        InventoryAndChests.grabY=e.getY();
        InventoryAndChests.mouseX=e.getX();
        InventoryAndChests.mouseY=e.getY();
        InventoryAndChests.inMove=true;
    }

    public void mouseReleased(MouseEvent e){
        InventoryAndChests.inMove=false;
    }
}
