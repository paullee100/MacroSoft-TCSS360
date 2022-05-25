package main.data;

import java.util.*;

public class Item {
    private ArrayList<ItemFile> files;

    public Item() {
        files = new ArrayList<ItemFile>();
    }

    public void addFile(ItemFile file) {
        files.add(file);
    }

    public ArrayList<ItemFile> getFiles() {
        return (ArrayList<ItemFile>)files.clone();
    }
}
