package main.data;

public class ItemFile {
    private String filePath;
    private String fileName;

    public ItemFile() {
        filePath = "";
        fileName = "";
    }

    public ItemFile(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath(){
        return filePath;
    }

    public String getFileName(){
        return fileName;
    }

}
