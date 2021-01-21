package LOGIC;

import javax.swing.Timer;
import java.awt.*;

public class GameLogic {
    public static entity PLAYER;
    private static Timer timer;
    private static Graphics graphics;

    public GameLogic(Graphics graphics){
        GameLogic.graphics =graphics;
    }

    public void initialise(int x, int y){
        PLAYER=new entity("player",x,y);
        timer = new Timer(15,new Loops(graphics));
    }
}
