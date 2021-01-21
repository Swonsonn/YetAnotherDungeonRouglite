package LOGIC;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KB implements KeyListener{
    private static boolean[] keys;

    public KB(){keys=new boolean[101];}

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()]=true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }

    public static boolean get(int key){return keys[key];}
}
