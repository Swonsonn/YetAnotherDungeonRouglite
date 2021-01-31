package InputReaders;

import GRAPHICS.GameScreen;
import LOGIC.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Loops implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(!GameScreen.INVActive){
            if(KB.get(KeyEvent.VK_W))
                GameLogic.move("player",0,-1);
            if(KB.get(KeyEvent.VK_S))
                GameLogic.move("player",0,1);
            if(KB.get(KeyEvent.VK_D))
                GameLogic.move("player",1,0);
            if(KB.get(KeyEvent.VK_A))
                GameLogic.move("player",-1,0);
            if(KB.get(KeyEvent.VK_E) && GameLogic.ChestIsNearBy)
                GameLogic.openChest();
        }
    }
}
