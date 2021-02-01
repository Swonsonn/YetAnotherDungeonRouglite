package INFOREADERS;

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
        String name, desc;
        int t, x, y;
        float s1=0,s2=0,s3=0;
        for(File file:folder.listFiles()){
            Scanner scanner=new Scanner(file);
            name=scanner.nextLine();
            desc= scanner.nextLine();
            t= scanner.nextInt();
            x= scanner.nextInt();
            y= scanner.nextInt();
            if(scanner.hasNext())s1= scanner.nextFloat();
            if(scanner.hasNext())s2= scanner.nextFloat();
            if(scanner.hasNext())s3= scanner.nextFloat();
            item i=new item(name,desc,t,x,y,s1,s2,s3);
            list.put(ID,i);
            System.out.println("[Items]"+file.getName()+" loaded");
            ++ID;
        }
        NUMOFITEMS=ID-1;
    }

    public static item get(int ID){return list.get(ID);}
}
