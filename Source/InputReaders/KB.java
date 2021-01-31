package InputReaders;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.ArrayIndexOutOfBoundsException;

public class KB implements KeyListener{
    private static boolean[] keysInput;
    private static boolean[] keysBlock;

    public KB(){
        keysInput=new boolean[190];
        keysBlock=new boolean[190];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        try{keysInput[e.getKeyCode()]=true;}catch(ArrayIndexOutOfBoundsException E){}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try{keysInput[e.getKeyCode()]=false;}catch(ArrayIndexOutOfBoundsException E){}
    }

    public static boolean get(int key){
        if(keysInput[key]==true && keysBlock[key]==false){
            keysBlock[key]=true;
            return true;
        }
        if(keysInput[key]==false)
            keysBlock[key]=false;
        return false;
    }
}
