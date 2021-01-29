package GRAPHICS;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FontManager {
    private static HashMap<String, File> fonts;

    public static void loadRes(){
        fonts=new HashMap<String, File>();
        File folder = new File("Resources/Fonts");
        for(File file:folder.listFiles()){
            fonts.put(file.getName().replaceAll(".ttf",""), file);
            System.out.println("[Fonts]"+file.getName()+" loaded");
        }
    }

    public static Font get(String key){
        try {
            Font font=Font.createFont(Font.TRUETYPE_FONT, fonts.get(key));
            return font.deriveFont(24f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
