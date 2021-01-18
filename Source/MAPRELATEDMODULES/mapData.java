package MAPRELATEDMODULES;

import java.util.Arrays;
import java.util.Random;

public class mapData {
    private char[][] MapScale1;
    private char[][] MapScale5;
    private int[][] CordsOfRooms;
    private String[] MAP;
    private int RoomCounter;
    private int WidthScale1;
    private int HeightScale1;
    private int WidthScale5;
    private int HeightScale5;
    private int EnterX;
    private int EnterY;
    private char filler='W';
    private static Random rand;
    private int ri;

    public mapData(long Seed){rand=new Random(Seed);}

    public void setSize(int WidthScale1, int HeightScale1){
        this.HeightScale1=HeightScale1;
        this.WidthScale1=WidthScale1;
        MapScale1=new char[WidthScale1][WidthScale1];
    }

    private boolean contains(int[] array, int num){
        for(int i=0;i< array.length;++i)
            if(array[i]==num) return true;
        return false;
    }
    
    public void generateSkeleton(){
        for(int j=0;j<HeightScale1;++j){for(int i=0;i<WidthScale1;++i){set(i,j,'W');}}
        int numOfRooms=rand.nextInt((int)((WidthScale1*HeightScale1)*0.66));//maximum amount of all rooms
        RoomCounter=(int)(numOfRooms*0.43);
        ri=0;
        int CurrentNumOfRooms=2;//current amount of rooms
        int[][] usedCords=new int[numOfRooms*10][2];//cords of created rooms 0-x, 1-y
        setEnterPoint();
        usedCords[0][0]=EnterX;
        usedCords[0][1]=EnterY;
        usedCords[1][0]=EnterX;
        usedCords[1][1]=EnterY-1;
        set(EnterX,EnterY-1,'O');
        while(CurrentNumOfRooms<numOfRooms){
            int dir=-1;//local var for direction
            int sx,sy;//cords of points, where rooms will continue or not
            int temp=rand.nextInt(CurrentNumOfRooms);//temp - from picked cords to direction
            int counter=0;//counter of unsuccessful tries to find root
            sx=usedCords[temp][0];
            sy=usedCords[temp][1];
            while(dir==-1 && counter<10){
                temp= rand.nextInt(4)+1;//1-up 2-down 3-right 4-left
                switch(temp){
                    case 1:{--sy;break;}
                    case 2:{++sy;break;}
                    case 3:{++sx;break;}
                    case 4:{--sx;break;}
                }
                if(sx>=0 && sx<WidthScale1 && sy>=0 && sy<HeightScale1){if(MapScale1[sx][sy]=='W')dir=temp;}
                ++counter;
            }
            int tx=0,ty=0;
            counter=0;
            if(dir!=-1 && sx>=0 && sx<WidthScale1 && sy>=0 && sy<HeightScale1){
                while(rand.nextInt(100)>35*(counter-3)){
                    set(sx,sy,'O');
                    usedCords[CurrentNumOfRooms][0]=sx;
                    usedCords[CurrentNumOfRooms][1]=sy;
                    ++CurrentNumOfRooms;
                    ++counter;
                    switch(dir){
                        case 1:{--sy;ty=sy-1;tx=sx;break;}
                        case 2:{++sy;ty=sy+1;tx=sx;break;}
                        case 3:{++sx;tx=sx+1;ty=sy;break;}
                        case 4:{--sx;tx=sx-1;ty=sy;break;}
                    }
                    if(tx<0 || tx>=WidthScale1 || ty<0 || ty>=HeightScale1){counter=100;}else
                    if(MapScale1[tx][ty]=='O' || MapScale1[tx][ty]=='X')
                        counter=100;
                }
            }
        }
    }

    private void solidWall(int x, int y){
        for(int X=x*4;X<=x*4+4;++X){
            for(int Y=y*4;Y<=y*4+4;++Y){
                MapScale5[X][Y]='W';
            }
        }
    }

    private void room(int x, int y){
        for(int Y=y*4;Y<=y*4+4;++Y)
            for(int X=x*4;X<=x*4+4;++X){
                MapScale5[X][Y]='W';
            }
        int t= rand.nextInt(100)+1;
        if(t>=60 && RoomCounter>ri){
            for(int X=x*4+1;X<=x*4+3;++X){
                for(int Y=y*4+1;Y<=y*4+3;++Y){
                    MapScale5[X][Y]='f';
                }
            }
            ++ri;
        }
        MapScale5[x*4+2][y*4+2]='f';
        if(x!=0)
            if(MapScale1[x-1][y]=='O' || MapScale1[x-1][y]=='X'){
                MapScale5[x*4][y*4+2]='f';MapScale5[x*4+1][y*4+2]='f';}
        if(x!=WidthScale1-1)
            if(MapScale1[x+1][y]=='O' || MapScale1[x+1][y]=='X'){
                MapScale5[x*4+4][y*4+2]='f';MapScale5[x*4+3][y*4+2]='f';}
        if(y!=0)
            if(MapScale1[x][y-1]=='O' || MapScale1[x][y-1]=='X'){
                MapScale5[x*4+2][y*4]='f';MapScale5[x*4+2][y*4+1]='f';}
        if(y!=HeightScale1-1)
            if(MapScale1[x][y+1]=='O' || MapScale1[x][y+1]=='X'){
                MapScale5[x*4+2][y*4+4]='f';MapScale5[x*4+2][y*4+3]='f';}
    }

    private void enterRoom(int x, int y){
        for(int X=x*4+1;X<=x*4+3;++X){
            for(int Y=y*4+1;Y<=y*4+3;++Y){
                MapScale5[X][Y]='f';
            }
        }
        MapScale5[x*4+2][y*4+2]='X';
        if(x!=0)
            if(MapScale1[x-1][y]=='O'){
                MapScale5[x*4][y*4+2]='f';MapScale5[x*4+1][y*4+2]='f';}
        if(x!=WidthScale1-1)
            if(MapScale1[x+1][y]=='O'){
                MapScale5[x*4+4][y*4+2]='f';MapScale5[x*4+3][y*4+2]='f';}
        if(y!=0)
            if(MapScale1[x][y-1]=='O'){
                MapScale5[x*4+2][y*4]='f';MapScale5[x*4+2][y*4+1]='f';}
        if(y!=HeightScale1-1)
            if(MapScale1[x][y+1]=='O'){
                MapScale5[x*4+2][y*4+4]='f';MapScale5[x*4+2][y*4+3]='f';}
    }

    public void generateFullSize(){
        WidthScale5=1+(WidthScale1*4);
        HeightScale5=1+(HeightScale1*4);
        MapScale5=new char[WidthScale5][HeightScale5];
        for(int y=0;y<HeightScale1;++y){
            for(int x=0;x<WidthScale1;++x){
                switch(MapScale1[x][y]){
                    case 'W':{
                        solidWall(x,y);
                        break;
                    }
                    case 'O':{
                        room(x,y);
                        break;
                    }
                    case 'X':{
                        enterRoom(x,y);
                        break;
                    }
                }
            }
        }
    }

    public String[] get(){
        MAP=new String[1+(HeightScale1*4)];
        for(int j=0;j<HeightScale5;++j){
            MAP[j]="";
            for(int i=0;i<WidthScale5;++i)
                MAP[j]+=Character.toString(MapScale5[i][j]);}
        return MAP;
    }

    private void set(int x, int y, char symbol){MapScale1[x][y]=symbol;}

    public void DEBUTStandalone(){
        for(int j=0;j<HeightScale1;++j){
            for(int i=0;i<WidthScale1;++i)
                System.out.print(MapScale1[i][j]);
            System.out.println("");
        }
    }

    private void setEnterPoint(){
        EnterX=rand.nextInt((int)(WidthScale1*0.30))+(int)(WidthScale1*0.35);
        EnterY=rand.nextInt((int)(HeightScale1*0.30))+(int)(HeightScale1*0.35);
        set(EnterX,EnterY,'X');
    }
}
