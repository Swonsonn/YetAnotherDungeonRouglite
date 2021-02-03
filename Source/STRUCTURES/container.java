package STRUCTURES;

import INFOREADERS.ItemList;

public class container {
    private int height;
    private int width;
    private containerUnit[][] container;

    public int MoveX;
    public int MoveY;

    private void initialise(){
        container=new containerUnit[width][height];
        for(int y=0;y<height;++y) {
            for (int x = 0; x < width; ++x) {
                container[x][y]=new containerUnit();
            }
        }
        MoveY=-1;
        MoveX=-1;
    }

    public container(int height, int width){
        this.height=height;
        this.width=width;
        initialise();
    }

    public boolean add(int ID, int X, int Y){
        item Item=ItemList.get(ID);
        if(Item.getName()!="empty"){
            for(int x=0;x<Item.x();++x){
                for(int y=0;y<Item.y();++y){
                    if(container[X+x][Y+y].getName()!="empty")
                        return false;
                }
            }
            for(int x=0;x<Item.x();++x){
                for(int y=0;y<Item.y();++y){
                    container[X+x][Y+y].set(Item.getName(),y*10+x);
                }
            }
        }
        return true;
    }

    public int X(int x, int y){
        int X=1;
        while(x+X<8)
            if(container[x+X][y].getPart()==X){
                ++X;}else{break;}
        return X;
    }

    public int Y(int x, int y){
        int Y=1;
        while(y+Y<4)
            if(container[x][y+Y].getPart()==Y*10){
                ++Y;}else{break;}
        return Y;
    }

    public int getPart(int x, int y){return container[x][y].getPart();}

    public int pop(int x, int y){//max - 5x5
        if(container[x][y].getName()=="empty")
            return -1;
        int mx=1,my=0;
        while(container[x][y+my].getPart()==my*10+mx){
            while(container[x+mx][y+my].getPart()==my*10+mx)
                ++mx;
            ++my;
        }
        String tempName=container[x][y].getName();
        containerUnit[][] temp=new containerUnit[mx][my];
        for(int X=0;X<mx;++X)
            for(int Y=0;Y<my;++Y){
                container[x+X][y+Y].set("empty",1);
                temp[X][Y]=new containerUnit();
                temp[X][Y].set(tempName,Y*10+X);
            }
        item I=ItemList.get(ItemStringToIntegerAdapter.get(tempName));
        return I.getID();
    }

    public String take(int x, int y){//max - 5x5
        return container[x][y].getName();
    }
}
