package main.data;

import java.io.IOException;
import java.util.*;
import java.io.File;
import org.json.*;
import java.io.*;

public class Database {
    private String workingDir;
    private ArrayList<Item> items;

    public Database(String workingDir) {
        this.workingDir = workingDir;
        items = new ArrayList<Item>();
    }

    public Database(JSONObject json) throws IllegalArgumentException {
        if (!json.has("workingDir"))
            throw new IllegalArgumentException("JSONObject must have a workingDir key");
        if (!json.has("items"))
            throw new IllegalArgumentException("JSONObject must have a items key");

        this.workingDir = json.getString("workingDir");

        JSONArray itemsArray = json.getJSONArray("items");
        items = new ArrayList<Item>();
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject itemObj = itemsArray.getJSONObject(i);
            items.add(new Item(itemObj));
        }
    }

    public void addItem(Item item) throws IllegalArgumentException {
        if (hasItem(item.getName()))
            throw new IllegalArgumentException("Item with same name already exists");

        items.add(item);
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }

    public boolean hasItem(String name) {
        return getItem(name) != null;
    }

    public Item[] getItems() {
        return (Item[])items.toArray();
    }

    public JSONObject toJSONObject() {
        List<JSONObject> itemsArray = new ArrayList<JSONObject>();
        for (Item item : items)
            itemsArray.add(item.toJSONObject());
        JSONObject obj = new JSONObject();
        obj.put("workingDir", workingDir);
        obj.put("items", new JSONArray(itemsArray));
        return obj;
    }
    public void save() throws IOException {
        File dir = new File(workingDir);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }

        File dbFile = new File(workingDir + "/database.json");
        if (dbFile.exists()) {
            System.out.println("dbFile exists");
        }
        else
            System.out.println("dbFile does not exist");

        JSONObject json = toJSONObject();

        FileWriter fw = new FileWriter(dbFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(json.toString());
        bw.close();
    }




    public void cacheFiles() {
        for (Item item : items) {
            for (ItemFile itemFile : item.getFiles()) {
                if (!(new File(itemFile.getPath()).getAbsolutePath().startsWith((new File(workingDir).getAbsolutePath())))) {
                    File itemDir = new File(workingDir + "/files/" + item.getName());
                    if (!itemDir.exists() || !itemDir.isDirectory())
                        itemDir.mkdir();

                    //File targetFile = new File(workingDir + "/files/" + item.getName() + "/" + itemFile.getName() + itemFile.getPath().getex)
                }
            }
        }
    }
}
