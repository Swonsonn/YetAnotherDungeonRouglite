package MAPRELATEDMODULES;

import java.util.Arrays;
import java.util.Random;

public class mapData {
    private char[][] MapScale1;
    private char[][] MapScale5;
    private String[] MAP;
    private int WidthScale1;
    private int HeightScale1;
    private int WidthScale5;
    private int HeightScale5;
    private int EnterX;
    private int EnterY;
    private char filler='W';
    private Random rand;

    public mapData(long Seed){rand=new Random(Seed);}

    public void setSize(int WidthScale1, int HeightScale1){
        this.HeightScale1=HeightScale1;
        this.WidthScale1=WidthScale1;
        MapScale1=new char[WidthScale1][WidthScale1];
    }

    private void floorTree(){
        int NumberOfNodes=rand.nextInt(Math.abs((int)(WidthScale1*0.3)))+1;
        for(int i=0;i<NumberOfNodes;++i){
            int x=rand.nextInt(WidthScale1);
            if(MapScale1[x][0]=='W')set(x,0,'O');
        }
    }

    private void floorTree(int y){
        int NodesOnThisLayer=0;
        for(int x=0;x<WidthScale1;++x){
            if(MapScale1[x][y-1]=='O'){
                int NumOfNodesBefore=0;
                int tempY=y-1;
                while(tempY>=0){
                    if(MapScale1[x][tempY]=='O'){NumOfNodesBefore++;}else{tempY=-1;}
                    --tempY;
                }
                if(NumOfNodesBefore<=2){
                    set(x,y,'O');
                    NodesOnThisLayer++;
                }else{
                    if(rand.nextInt(1000)+1>150*NumOfNodesBefore){
                        set(x,y,'O');
                        NodesOnThisLayer++;
                    }
                }
            }
        }

        NodesOnThisLayer=rand.nextInt(Math.abs((int)(WidthScale1*0.3)))+1-NodesOnThisLayer;
        for(int i=0;i<NodesOnThisLayer;++i){
            int x=rand.nextInt(WidthScale1);
            if(MapScale1[x][y]=='W')set(x,y,'O');
        }

        for(int x=0;x<WidthScale1;++x){
            if(MapScale1[x][y]=='O'){
                int LeftRight= rand.nextInt(6)+1;
                int Length=rand.nextInt(Math.abs((int)(WidthScale1*0.32)))+2;
                switch(LeftRight){
                    case 1:{//Left
                        int tempX=x-1;
                        while(tempX>=0 && Length>0){
                            set(tempX,y,'O');
                            --tempX;
                            --Length;
                        }
                        break;
                    }
                    case 2:{//Right
                        int tempX=x+1;
                        while(tempX<WidthScale1 && Length>0){
                            set(tempX,y,'O');
                            ++tempX;
                            --Length;
                        }
                        break;
                    }
                    case 3:case 4:case 5:case 6:{break;}
                }
            }
        }
    }

    private boolean contains(int[] array, int num){
        for(int i=0;i< array.length;++i)
            if(array[i]==num) return true;
        return false;
    }

    private void uniteParts(){
        int NumOfConnectors= rand.nextInt(Math.abs((int)(HeightScale1*0.32)));
        int[] usedY=new int[NumOfConnectors];
        int y= rand.nextInt(HeightScale1);
        for(int i=0;i<NumOfConnectors;++i){
            while(contains(usedY,y)){
                y= rand.nextInt(HeightScale1);
            }
            usedY[i]=y;
            int first=-1, last=-1;
            for(int x=0;x<WidthScale1;++x){
                if(MapScale1[x][y]=='O'){
                    if(first==-1){
                        first=x;
                    }else{last=x;}
                }
            }
            for(int x=first;x<=last;++x)
                set(x,y,'O');
        }
    }
    
    public void generateScale1(){
        for(int j=0;j<HeightScale1;++j){for(int i=0;i<WidthScale1;++i){set(i,j,'W');}}
        floorTree();
        for(int i=1;i<HeightScale1;++i)floorTree(i);
        uniteParts();

        generateScale5();
    }

    private void solidWall(int x, int y){
        for(int X=x*4;X<=x*4+4;++X){
            for(int Y=y*4;Y<=y*4+4;++Y){
                MapScale5[X][Y]='W';
            }
        }
    }

    private void room(int x, int y){
        for(int Y=y*4;Y<=y*4+4;++Y){
            MapScale5[x*4][Y]='W';
            MapScale5[x*4+4][Y]='W';
        }
        for(int X=x*4;X<=x*4+4;++X){
            MapScale5[X][y*4]='W';
            MapScale5[X][y*4+4]='W';
        }
        if(x!=0)
            if(MapScale1[x-1][y]=='O')
                MapScale5[x*4][y*4+2]=' ';
        if(x!=WidthScale1-1)
            if(MapScale1[x+1][y]=='O')
                MapScale5[x*4+4][y*4+2]=' ';
        if(y!=0)
            if(MapScale1[x][y-1]=='O')
                MapScale5[x*4+2][y*4]=' ';
        if(y!=HeightScale1-1)
            if(MapScale1[x][y+1]=='O')
                MapScale5[x*4+2][y*4+4]=' ';
    }

    private void enterRoom(int x, int y){
        for(int X=x*4+1;X<=x*4+3;++X){
            for(int Y=y*4+1;Y<=y*4+3;++Y){
                MapScale5[X][Y]='f';
            }
        }
        MapScale5[x*4+2][y*4+2]='X';
    }

    private void generateScale5(){
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
        for(int j=0;j<HeightScale5;++j)
            for(int i=0;i<WidthScale5;++i)
                MAP[j]+=Character.toString(MapScale5[i][j]);
        return MAP;
    }

    public void DEBUG(){
        generateScale1();
        generateScale5();
        //for(int j=0;j<HeightScale1;++j){for(int i=0;i<WidthScale1;++i){System.out.print(MapScale1[i][j]);}System.out.println("");}
        for(int j=0;j<HeightScale5;++j){for(int i=0;i<WidthScale5;++i){System.out.print(MapScale5[i][j]);}System.out.println("");}
    }

    private void set(int x, int y, char symbol){MapScale1[x][y]=symbol;}

    private void setEnterPoint(){

    }
}
