package STRUCTURES;

public class container {
    private int height;
    private int width;
    private containerUnit[][] container;

    private void initialise(){
        container=new containerUnit[width][height];
        for(int y=0;y<height;++y) {
            for (int x = 0; x < width; ++x) {
                container[x][y]=new containerUnit();
            }
        }
    }

    public container(int height, int width){
        this.height=height;
        this.width=width;
        initialise();
    }

    public void add(String name, int part, int x, int y){
        container[x][y].set(name, part);
    }

    public containerUnit[][] take(int x, int y){//max - 5x5
        containerUnit[][] ITEM;
        String Iname=container[x][y].getName();
        int sx=1,sy=1;
        for(int i=1;i<5;++i){
            if(container[x+i][y].getPart()==1+i)++sx;
            if(container[x][y+i].getPart()==1+i)++sy;
        }
        ITEM=new containerUnit[sx][sy];
        for(int xx=0;xx<sx;++xx)
            for(int yy=0;yy<sy;++yy)
                ITEM[xx][yy].set(Iname,yy*5+1+xx);
        return ITEM;
    }
}
