package MAPRELATEDMODULES;

import java.util.Random;

public class mapData {
    private char[][] MapScale1;
    private char[][] MapScale5;
    private char[][] RoomMap;
    private int[][] Chests;
    private String[] MAP;
    private int RoomCounter;
    private int WidthScale1;
    private int HeightScale1;
    private int WidthScale5;
    private int HeightScale5;
    private int EnterX;
    private int EnterY;
    private static Random rand;
    private int ri;

    public mapData(long Seed){rand=new Random(Seed);System.out.println("[MapGeneration]Map generation started with seed "+Seed);}

    public void setSize(int WidthScale1, int HeightScale1){
        this.HeightScale1=HeightScale1;
        this.WidthScale1=WidthScale1;
        MapScale1=new char[WidthScale1][WidthScale1];
        RoomMap=new char[WidthScale1][WidthScale1];
        System.out.println("[MapGeneration]Map size set "+WidthScale1+"x"+HeightScale1);
    }

    public void generateSkeleton(){
        System.out.println("[MapGeneration]Map skeleton generation started");
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
        for(int y=0;y<HeightScale1;++y)
            for(int x=0;x<WidthScale1;++x)
                RoomMap[x][y]='w';
        System.out.println("[MapGeneration]Map skeleton generation ended");
    }

    private void wall(int x, int y){
        for(int X=x*4;X<=x*4+4;++X){
            for(int Y=y*4;Y<=y*4+4;++Y){
                MapScale5[X][Y]='D';
            }
        }
    }

    private void holeFixer(){
        for(int y=0;y<HeightScale1-1;++y){
            for(int x=0;x<WidthScale1-1;++x){
                if((MapScale1[x][y] == 'O' || MapScale1[x][y]=='X') && MapScale1[x+1][y]=='W'){
                    for(int i=0;i<=4;++i)
                        MapScale5[x*4+4][y*4+i]='W';
                }
                if((MapScale1[x][y] == 'O' || MapScale1[x][y]=='X') && MapScale1[x][y+1]=='W'){
                    for(int i=0;i<=4;++i)
                        MapScale5[x*4+i][y*4+4]='W';
                }
            }
        }
        if(MapScale1[EnterX-1][EnterY]=='W'){
            for(int i=0;i<=4;++i)
                MapScale5[EnterX*4][EnterY*4+i]='W';
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
            RoomMap[x][y]='o';
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

    private void uniteRooms(){
        for(int y=0;y<HeightScale1-1;++y){
            for(int x=0;x<WidthScale1-1;++x){
                if(RoomMap[x][y]=='o'){
                    if(RoomMap[x+1][y]=='o'){
                        MapScale5[x*4+4][y*4+1]='f';
                        MapScale5[x*4+4][y*4+3]='f';
                    }
                    if(RoomMap[x][y+1]=='o'){
                        MapScale5[x*4+1][y*4+4]='f';
                        MapScale5[x*4+3][y*4+4]='f';
                    }
                }
            }
        }
    }

    private void putChests(){
        Chests=new int[RoomCounter][2];//0-x, 1-y
        int cur=0;
        for(int y=1;y<HeightScale1-1;++y){
            for(int x=1;x<WidthScale1-1;++x){
                int ran= rand.nextInt(30)+70;
                if(RoomMap[x][y]=='o' && ran>=90){
                    boolean placed=false;
                    while(!placed){
                        int r= rand.nextInt(5);//1-left, 2-up, 3-right, 4-down, 0-center
                        switch(r){
                            case 0:{
                                Chests[cur][0]=x*4+2;
                                Chests[cur][1]=y*4+2;
                                ++cur;
                                placed=true;
                                break;
                            }
                            case 1:{
                                if(RoomMap[x-1][y]=='w'){
                                    int temp= rand.nextInt(3);
                                    Chests[cur][0]=x*4+1;
                                    Chests[cur][1]=y*4+1+temp;
                                    ++cur;
                                    placed=true;
                                }
                                break;
                            }
                            case 2:{
                                if(RoomMap[x][y-1]=='w'){
                                    int temp= rand.nextInt(3);
                                    Chests[cur][0]=x*4+1+temp;
                                    Chests[cur][1]=y*4+1;
                                    ++cur;
                                    placed=true;
                                }
                                break;
                            }
                            case 3:{
                                if(RoomMap[x+1][y]=='w'){
                                    int temp= rand.nextInt(3);
                                    Chests[cur][0]=x*4+3;
                                    Chests[cur][1]=y*4+1+temp;
                                    ++cur;
                                    placed=true;
                                }
                                break;
                            }
                            case 4:{
                                if(RoomMap[x][y+1]=='w'){
                                    int temp= rand.nextInt(3);
                                    Chests[cur][0]=x*4+1+temp;
                                    Chests[cur][1]=y*4+3;
                                    ++cur;
                                    placed=true;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        Chests[cur][0]=-1;
    }

    public void generateFullSize(){
        System.out.println("[MapGeneration]Full size map generation started");
        WidthScale5=1+(WidthScale1*4);
        HeightScale5=1+(HeightScale1*4);
        MapScale5=new char[WidthScale5][HeightScale5];
        for(int y=0;y<HeightScale1;++y){
            for(int x=0;x<WidthScale1;++x){
                switch(MapScale1[x][y]){
                    case 'W':{
                        wall(x,y);
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
        uniteRooms();
        holeFixer();
        EnterY=EnterY*4+2;
        EnterX=EnterX*4+2;
        putChests();
        System.out.println("[MapGeneration]Full size map generation ended");
    }

    public String[] get(){
        MAP=new String[1+(HeightScale1*4)];
        for(int j=0;j<HeightScale5;++j){
            MAP[j]="";
            for(int i=0;i<WidthScale5;++i)
                MAP[j]+=Character.toString(MapScale5[i][j]);}
        return MAP;
    }

    public int[][] getChests(){return Chests;}

    private void set(int x, int y, char symbol){MapScale1[x][y]=symbol;}

    public int X(){return EnterX;}

    public int Y(){return EnterY;}

    private void setEnterPoint(){
        EnterX=rand.nextInt((int)(WidthScale1*0.30))+(int)(WidthScale1*0.35);
        EnterY=rand.nextInt((int)(HeightScale1*0.30))+(int)(HeightScale1*0.35);
        System.out.println("[MapGeneration]Enter on map set at X:"+EnterX+" Y:"+EnterY);
        set(EnterX,EnterY,'X');
    }
}
