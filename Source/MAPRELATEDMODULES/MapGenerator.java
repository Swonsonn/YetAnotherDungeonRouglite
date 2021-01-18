package MAPRELATEDMODULES;

import MAPRELATEDMODULES.mapData;

public class MapGenerator {
    private mapData data;
    public int SizeW;
    public int SizeH;

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
        data.generateSkeleton();
        data.generateFullSize();
        SizeW=1+(SizeW*4);
        SizeH=1+(SizeH*4);
        return data.get();
    }
}
