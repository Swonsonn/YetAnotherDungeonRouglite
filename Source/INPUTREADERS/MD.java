package INPUTREADERS;

import GRAPHICS.InventoryAndChests;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MD extends MouseMotionAdapter {
    public void mouseDragged(MouseEvent e){
        InventoryAndChests.mouseX=e.getX();
        InventoryAndChests.mouseY=e.getY();
    }
}
