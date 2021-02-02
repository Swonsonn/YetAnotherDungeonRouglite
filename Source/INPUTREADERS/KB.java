package INPUTREADERS;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.ArrayIndexOutOfBoundsException;

public class KB implements KeyListener{
    private static boolean[] keysInput;
    private static int[] keysTimer;

    public KB(){
        keysInput=new boolean[190];
        keysTimer=new int[190];
        for(int i=0;i<190;++i)
            keysTimer[i]=-1;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        try{
            keysInput[e.getKeyCode()]=true;
        }catch(ArrayIndexOutOfBoundsException E){};
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try{
            keysInput[e.getKeyCode()]=false;
        }catch(ArrayIndexOutOfBoundsException E){};
    }

    public static boolean get(int key){
        if(keysInput[key]==true){
            ++keysTimer[key];
            if(keysTimer[key]>=10)
                keysTimer[key]=0;
            if(keysTimer[key]==0){
                return true;
            }
        }else{
            keysTimer[key]=-1;
        }
        return false;
    }
}
