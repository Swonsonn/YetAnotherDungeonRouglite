package STRUCTURES;

public class containerUnit {
    private String NAME;
    private int PART;//items can take not only 1, but several slots to store(1x2, 2x2 etc) This is pointer of which part is contained

    public containerUnit(){
        NAME="empty";
        PART=1;
    }

    public void set(String NAME, int PART){
        this.NAME=NAME;
        this.PART=PART;
    }

    public String getName(){return NAME;}
    public int getPart(){return PART;}
}
