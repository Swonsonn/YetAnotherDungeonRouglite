package MAPRELATEDMODULES;

public class MapGenerator {
    private static mapData data;
    private static int Y;
    private static int X;

    public static void initialise(int Width, int Height, long Seed){
        data = new mapData(Seed);
        data.setSize(Width, Height);
        X=1+(Width*4);
        Y=1+(Height*4);
        data.generateSkeleton();
        data.generateFullSize();
    }

    public static String[] getMAP(){
        return data.get();
    }

    public static int getHeight(){return Y;}
    public static int getWidth(){return X;}

    public static int getEnterX(){return data.X();}
    public static int getEnterY(){return data.Y();}

    public static int[][] getChests(){return data.getChests();}
}
