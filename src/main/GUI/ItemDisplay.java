package main.GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.tabs.InsertDocument;
import main.data.Database;
import main.data.Item;
import main.data.ItemFile;

public class ItemDisplay {

    private final Stage myStage;

    private GridPane mainPane;
    private final VBox fileDisplay;
    private Button[] fileButtons;
    private final Text itemNameDisplay;

    private final Font fileButtonFont;

    public ItemDisplay(Stage theStage){
        myStage = theStage;

        itemNameDisplay = new Text("Error: No Item Name");
        itemNameDisplay.getStyleClass().add("white-text");
        itemNameDisplay.setFont(new Font(42));
        fileButtonFont = new Font(24);

        fileDisplay = new VBox();
    }

    public Pane buildView(Item theItem){
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.TOP_CENTER);

        itemNameDisplay.setText(theItem.getName());

        ItemFile[] theFiles = theItem.getFiles();

        fileButtons = new Button[theFiles.length];

        //Desktop is used for opening files
        Desktop desktop = Desktop.getDesktop();

        for(int i = 0; i < theFiles.length; i++){
            //Initializes the item button
            Button currFileButton = new Button("Error...");
            currFileButton.getStyleClass().add("transparent-square-button");
            currFileButton.setFont(fileButtonFont);


            File selectedFile = new File(theFiles[i].getPath());
            currFileButton.setText(theFiles[i].getName() + ": " + selectedFile.getName());
            int index = i;//action listeners can't take in i
            currFileButton.setOnAction(clickEvent -> {
                //Gets the file from the path
                //Makes sure the file exists
                if(selectedFile.exists()){
                    try {
                        desktop.open(selectedFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    System.out.println("Error: file does not exist");
                }
            });
            fileButtons[i] = currFileButton;
        }

        Button insertDocument = new Button();
        insertDocument.getStyleClass().add("squircle-button");
        insertDocument.setTooltip(new Tooltip("Insert New Document"));
        Image icon = new Image("/documentIcon.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(30); iconView.setFitWidth(30); //set the dimensions of the button
        insertDocument.setGraphic(iconView);
        insertDocument.setOnAction(clickEvent -> {
            int index = Database.db.getIndex(theItem);
            GUIController.guiControl.insertDocToItem(index);
        });

        mainPane.add(itemNameDisplay, 0, 0);
        mainPane.add(fileDisplay, 0, 1);
        mainPane.add(insertDocument, 1, 0);
        fileDisplay.setAlignment(Pos.CENTER);
        fileDisplay.getChildren().addAll(fileButtons);
        return mainPane;
    }

}
