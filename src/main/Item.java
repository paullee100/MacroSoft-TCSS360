package main;

import java.util.*;

public class Item {
    private ArrayList<String> files;

    public Item() {
        files = new ArrayList<String>();
    }


    public void addFile(String path) {
        files.add(path);
    }

    public ArrayList<String> getFiles() {
        return (ArrayList<String>)files.clone();
    }
}
