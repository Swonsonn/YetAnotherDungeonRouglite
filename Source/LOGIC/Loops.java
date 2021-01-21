package LOGIC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Loops implements ActionListener {
    private static Graphics graphics;

    public Loops(Graphics graphics){
        this.graphics=graphics;
    }

    @Override
    public void actionPerformed(ActionEvent ACT) {
        if(KB.get(KeyEvent.VK_W))
            GameLogic.PLAYER.setPos(-1,0);
        if(KB.get(KeyEvent.VK_S))
            GameLogic.PLAYER.setPos(1,0);
        if(KB.get(KeyEvent.VK_D))
            GameLogic.PLAYER.setPos(0,1);
        if(KB.get(KeyEvent.VK_A))
            GameLogic.PLAYER.setPos(0,-1);

        GameLogic.PLAYER.render(graphics);
    }
}
