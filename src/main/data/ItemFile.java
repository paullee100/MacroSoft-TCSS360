package main.data;
import org.json.*;
import org.json.JSONObject;
import java.util.HashMap;

public class ItemFile {
    private String filePath;
    private String fileName;

    public ItemFile() {
        filePath = "";
        fileName = "";
    }

    public ItemFile(String fileName, String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public ItemFile(JSONObject json) throws IllegalArgumentException {
        if (!json.has("filePath"))
            throw new IllegalArgumentException("JSONObject must have a filePath key");
        if (!json.has("fileName"))
            throw new IllegalArgumentException("JSONObject must have a fileName key");

        this.filePath = json.getString("filePath");
        this.fileName = json.getString("fileName");
    }

    public String getPath(){
        return filePath;
    }

    public String getName(){
        return fileName;
    }

    public JSONObject toJSONObject() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("filePath", filePath);
        map.put("fileName", fileName);
        return new JSONObject(map);
    }

}
