package GRAPHICS;

import INPUTREADERS.KB;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class Window {
    private static JFrame window;
    private static GameScreen gameScreen;

    public static final int WIDTH=1600;//1600
    public static final int HEIGHT=1000;//1000
    public static int PLAYERPOSX=0;
    public static int PLAYERPOSY=0;
    public static final int RES=24;
    public static final int invRES=48;

    public static void create() throws FileNotFoundException {
        window=new JFrame("GAME");
        window.setBounds(100,10, WIDTH, HEIGHT);
        window.setResizable(false);
        window.setLayout(new BorderLayout());
        gameScreen=new GameScreen();
        gameScreen.addKeyListener(new KB());
        gameScreen.setLayout(null);
        gameScreen.setBounds(0,0,WIDTH, HEIGHT);
        window.add(gameScreen);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("[System]Exiting the game");
                System.exit(0);
            }
        });
    }

    public static void setVisible(){
        if(window!=null) window.setVisible(true);
    }
}
