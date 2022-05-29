package main.data;

import java.util.*;
import org.json.*;

public class Item {
    private String name;
    private ArrayList<ItemFile> files;

    public Item(String name) {
        this.name = name;
        files = new ArrayList<ItemFile>();
    }

    public Item(JSONObject json) throws IllegalArgumentException {
        if (!json.has("name"))
            throw new IllegalArgumentException("JSONObject must have a name key");
        if (!json.has("files"))
            throw new IllegalArgumentException("JSONObject must have a files key");

        this.name = json.getString("name");

        files = new ArrayList<ItemFile>();
        JSONArray filesArray = json.getJSONArray("files");
        for (int i = 0; i < filesArray.length(); i++) {
            JSONObject obj = filesArray.getJSONObject(i);
            files.add(new ItemFile(obj));
        }
    }

    public String getName() {
        return name;
    }

    public void addFile(ItemFile file) throws IllegalArgumentException {
        if (hasFile(file.getName()))
            throw new IllegalArgumentException("File already exists with same name");

        files.add(file);
    }

    public void removeFile(ItemFile file) {
        if (file == null)
            return;

        files.remove(file);
    }

    public void removeFile(String name) {
        removeFile(getFile(name));
    }

    public ItemFile[] getFiles() {
        return (ItemFile[])files.toArray();
    }

    public ItemFile getFile(String name) {
        for (ItemFile f : files) {
            if (f.getName().equals(name))
                return f;
        }

        return null;
    }

    public boolean hasFile(String name) {
        return getFile(name) != null;
    }

    public JSONObject toJSONObject() {
        JSONArray filesArray = new JSONArray();
        for (ItemFile file : files) {
            filesArray.put(file.toJSONObject());
        }

        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("files", filesArray);

        return object;
    }
}
