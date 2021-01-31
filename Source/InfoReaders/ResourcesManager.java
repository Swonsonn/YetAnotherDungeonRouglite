package InfoReaders;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;

public class ResourcesManager {
    private static HashMap<String, BufferedImage> textures;

    public static void loadRes(){
        textures=new HashMap<String, BufferedImage>();
        File folder = new File("Resources/Textures");
        for(File file:folder.listFiles()){
            try{
                textures.put(file.getName().replaceAll(".png",""), ImageIO.read(file));
                System.out.println("[Resources]"+file.getName()+" loaded");
            }catch (IOException err){
                System.out.println("[Resources]Error: "+file.getName()+" can not be loaded");
            }
        }
    }

    public static BufferedImage get(String key){
        return textures.get(key);
    }
}
