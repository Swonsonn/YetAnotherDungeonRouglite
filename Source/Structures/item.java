package Structures;

public class item {
    private String name;
    private String description;
    private int type;//1-junk, 2-food, 3-weapon, 4-armor, 5-another
    private float stat1;
    private float stat2;
    private float stat3;

    public item(String name, String description, int type, float stat1, float stat2, float stat3){
        this.name=name;
        this.description=description;
        this.type=type;
        this.stat1=stat1;
        this.stat2=stat2;
        this.stat3=stat3;
    }

    public String getName(){return name;}
}
