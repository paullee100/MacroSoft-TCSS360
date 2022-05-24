package main.data;

import java.util.*;

public class Database {
    private ArrayList<Item> items;

    public Database() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<String> getItems() {
        return (ArrayList<String>)items.clone();
    }
}
