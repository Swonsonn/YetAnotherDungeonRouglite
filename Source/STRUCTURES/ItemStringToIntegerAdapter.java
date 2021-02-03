package STRUCTURES;

import java.util.HashMap;

public class ItemStringToIntegerAdapter {
    private static HashMap<String, Integer> Adapter;
    public static void initialise(){
        Adapter=new HashMap<String, Integer>();
    }

    public static void set(String name, Integer ID){
        Adapter.put(name,ID);
    }

    public static Integer get(String name){
        return Adapter.get(name);
    }
}
