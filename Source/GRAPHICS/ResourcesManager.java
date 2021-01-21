package GRAPHICS;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;

public class ResourcesManager {
    private static HashMap<String, BufferedImage> textures;

    public ResourcesManager(){
        textures=new HashMap<String, BufferedImage>();
        File folder = new File("Resources/Textures");
        for(File file:folder.listFiles()){
            try{
                textures.put(file.getName().replaceAll(".png",""), ImageIO.read(file));
            }catch (IOException err){

            }
        }
    }

    public static BufferedImage get(String key){
        return textures.get(key);
    }
}