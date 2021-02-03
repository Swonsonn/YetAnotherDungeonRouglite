package STRUCTURES;

import INFOREADERS.ItemList;

public class container {
    private final int height;
    private final int width;
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
        if(!Item.getName().equals("empty")){
            if(X+Item.x()<=width && Y+Item.y()<=height){
                for(int x=0;x<Item.x();++x){
                    for(int y=0;y<Item.y();++y){
                        if(!container[X + x][Y + y].getName().equals("empty"))
                            return false;
                    }
                }
                for(int x=0;x<Item.x();++x){
                    for(int y=0;y<Item.y();++y){
                        container[X+x][Y+y].set(Item.getName(),y*10+x);
                    }
                }
            }else{return false;}
        }
        return true;
    }

    public int X(int x, int y){
        int X=1;
        while(x+X<width)
            if(container[x+X][y].getPart()==X){
                ++X;}else{break;}
        return X;
    }

    public int Y(int x, int y){
        int Y=1;
        while(y+Y<height)
            if(container[x][y+Y].getPart()==Y*10){
                ++Y;}else{break;}
        return Y;
    }

    public int getPart(int x, int y){return container[x][y].getPart();}

    public int pop(int x, int y){//max - 5x5
        if(!container[x][y].getName().equals("empty")){
            String name=container[x][y].getName();
            for(int X=0;X<X(x,y);++X)
                for(int Y=0;Y<Y(x,y);++Y){
                    container[x+X][y+Y].set("empty",0);
                }
            return ItemStringToIntegerAdapter.get(name);
        }
        return -1;
    }

    public String take(int x, int y){//max - 5x5
        return container[x][y].getName();
    }
}
