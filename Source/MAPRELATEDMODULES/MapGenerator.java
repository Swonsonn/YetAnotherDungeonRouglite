package MAPRELATEDMODULES;

import MAPRELATEDMODULES.mapData;

public class MapGenerator {
    mapData data;
    int SizeW;
    int SizeH;

    public MapGenerator(int SizeW, int SizeH){
        this.SizeH=SizeH;
        this.SizeW=SizeW;
        data = new mapData(System.currentTimeMillis());
    }

    public MapGenerator(int SizeW, int SizeH, long Seed){
        this.SizeH=SizeH;
        this.SizeW=SizeW;
        data = new mapData(Seed);
    }

    public String[] Generate(){
        data.setSize(SizeW,SizeH);
        data.generateScale1();
        return data.get();
    }
}
