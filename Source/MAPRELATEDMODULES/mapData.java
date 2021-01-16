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
    
    private void generateScale1(){
        for(int j=0;j<HeightScale1;++j){for(int i=0;i<WidthScale1;++i){set(i,j,'W');}}
        floorTree();
        for(int i=1;i<HeightScale1;++i)floorTree(i);
        uniteParts();
    }

    public String[] get(){
        return MAP;
    }

    public void DEBUG(){
        generateScale1();
        for(int j=0;j<HeightScale1;++j){for(int i=0;i<WidthScale1;++i){System.out.print(MapScale1[i][j]);}System.out.println("");}
    }

    public void set(int x, int y, char symbol){MapScale1[x][y]=symbol;}

    public void setEnterPoint(int EnterX, int EnterY){this.EnterX=EnterX;this.EnterY=EnterY;MapScale1[EnterX][EnterY]='X';}
}
