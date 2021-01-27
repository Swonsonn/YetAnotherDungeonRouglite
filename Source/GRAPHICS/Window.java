package GRAPHICS;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {
    private static JFrame window;
    private static GameScreen screen;

    public static final int WIDTH=1600;//1600
    public static final int HEIGHT=1000;//1000
    public static final int RES=15;

    public static void create(){
        window=new JFrame("GAME");
        window.setBounds(0,0, WIDTH, HEIGHT);
        window.setResizable(false);
        screen=new GameScreen();
        window.add(screen);
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
