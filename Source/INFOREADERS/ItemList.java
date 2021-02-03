package INFOREADERS;

import STRUCTURES.ItemStringToIntegerAdapter;
import STRUCTURES.item;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ItemList {
    private static HashMap<Integer, item> list;
    public static int NUMOFITEMS;

    public static void loadRes() throws FileNotFoundException {
        list=new HashMap<Integer, item>();
        File folder = new File("Resources/Items");
        int ID=1;
        String name="empty", desc="";
        int t=0, x=1, y=1,id=0;
        float s1=0,s2=0,s3=0;
        for(File file:folder.listFiles()){
            Scanner scanner=new Scanner(file);
            while(scanner.hasNext()){
                String d="=";
                String[] msg=scanner.nextLine().split(d);
                switch(msg[0]){
                    case "Name":{name=msg[1];break;}
                    case "Description":{desc=msg[1];break;}
                    case "Type":{t=Integer.parseInt(msg[1]);break;}
                    case "ID":{id=Integer.parseInt(msg[1]);break;}
                    case "SizeX":{x=Integer.parseInt(msg[1]);break;}
                    case "SizeY":{y=Integer.parseInt(msg[1]);break;}
                    case "Stat1":{s1=Float.parseFloat(msg[1]);break;}
                    case "Stat2":{s2=Float.parseFloat(msg[1]);break;}
                    case "Stat3":{s3=Float.parseFloat(msg[1]);break;}
                }
            }
            item i=new item(name,desc,t,id,x,y,s1,s2,s3);
            ItemStringToIntegerAdapter.set(name,id);
            list.put(id,i);
            System.out.println("[Items]"+file.getName()+" loaded");
            ++ID;
        }
        NUMOFITEMS=ID-1;
    }

    public static item get(int ID){return list.get(ID);}
}
