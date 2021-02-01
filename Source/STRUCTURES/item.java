package STRUCTURES;

public class item {
    private String name;
    private String description;
    private int type;//1-junk, 2-food, 3-weapon, 4-armor, 5-another
    private int sizeX;
    private int sizeY;
    private float stat1;
    private float stat2;
    private float stat3;

    public item(String name, String description, int type, int sizeX, int sizeY, float stat1, float stat2, float stat3){
        this.name=name;
        this.description=description;
        this.type=type;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.stat1=stat1;
        this.stat2=stat2;
        this.stat3=stat3;
    }

    public String getName(){return name;}
    public String getDescription(){return description;}

    public int x(){return sizeX;}
    public int y(){return sizeY;}

    public float stat1(){return stat1;}
    public float stat2(){return stat2;}
    public float stat3(){return stat3;}
}
