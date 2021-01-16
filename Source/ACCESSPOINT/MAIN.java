package ACCESSPOINT;

import MAPRELATEDMODULES.MapGenerator;

public class MAIN {
    public static void main(String[] args){
        MapGenerator g = new MapGenerator(14,14);
        g.Generate();
    }
}
