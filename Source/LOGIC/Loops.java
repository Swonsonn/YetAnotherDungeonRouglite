package LOGIC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Loops implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(KB.get(KeyEvent.VK_W))
            GameLogic.move(0,-1);
        if(KB.get(KeyEvent.VK_S))
            GameLogic.move(0,1);
        if(KB.get(KeyEvent.VK_D))
            GameLogic.move(1,0);
        if(KB.get(KeyEvent.VK_A))
            GameLogic.move(-1,0);
    }
}
