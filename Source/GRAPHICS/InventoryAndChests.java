package GRAPHICS;

import INPUTREADERS.MC;
import INPUTREADERS.MD;
import LOGIC.GameLogic;
import javax.swing.*;
import java.awt.*;

public class InventoryAndChests extends JPanel {
    public static int grabX;
    public static int grabY;
    public static boolean inMove;

    public static int mouseX;
    public static int mouseY;

    public InventoryAndChests(){
        super();
        this.setBounds(100,100, Window.PLAYERPOSX*2-100, Window.PLAYERPOSY*2-200);
        this.setFocusable(false);
        this.setVisible(false);
        this.setBackground(Color.lightGray);
        this.addMouseListener(new MC());
        this.addMouseMotionListener(new MD());
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if(GameLogic.ChestIsNearBy){
            if(!inMove && grabX!=-1){
                int startX=0, startY=0, endX=-1, endY=-1;
                for(int x=0;x<8;++x){
                    for(int y=0;y<4;++y){
                        if(InventoryAndChests.grabX>=100+x * Window.invRES && InventoryAndChests.grabX<100+(x+1) * Window.invRES && InventoryAndChests.grabY>=100+y * Window.invRES && InventoryAndChests.grabY<100+(y+1) * Window.invRES){
                            startX=x;
                            startY=y;
                        }
                        if(InventoryAndChests.mouseX>=100+x * Window.invRES && InventoryAndChests.mouseX<100+(x+1) * Window.invRES && InventoryAndChests.mouseY>=100+y * Window.invRES && InventoryAndChests.mouseY<100+(y+1) * Window.invRES){
                            endX=x;
                            endY=y;
                        }
                    }
                }
                if(endX==-1 && endY==-1){
                    endX=startX;
                    endY=startY;
                }
                int  I=GameLogic.Chests[GameLogic.numOfChest].inv.pop(startX,startY);
                if(I!=-1){
                    if(!GameLogic.Chests[GameLogic.numOfChest].inv.add(I,endX,endY)){
                        GameLogic.Chests[GameLogic.numOfChest].inv.add(I,startX,startY);
                    }
                }
                grabX=-1;
                grabY=-1;
            }
            Render.renderINVchest(graphics);
        }
    }
}
