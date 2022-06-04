package main.GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.data.Database;
import main.data.Item;
import main.data.ItemFile;

public class ItemDisplay {

    private final Stage myStage;

    private GridPane headerPane;
    /** The pane that contains the header and file display*/
    private FlowPane filePane;
    private final ScrollPane fileDisplay;
    private Button[] fileButtons;
    private VBox buttonGroup;
    private final Text itemNameDisplay;

    private final TextArea descriptionField;

    private final Font fileButtonFont;

    private ItemFile[] itemFiles;

    private final double FILE_BUTTON_WIDTH = 500;
    private final double INS_DEL_BUTTON_WIDTH = 45;

    public ItemDisplay(Stage theStage){
        myStage = theStage;

        itemNameDisplay = new Text("Error: No Item Name");
        itemNameDisplay.getStyleClass().add("white-text");
        itemNameDisplay.setFont(new Font(42));
        fileButtonFont = new Font(24);

        descriptionField = new TextArea();
        descriptionField.getStyleClass().add("text-area");

        fileDisplay = new ScrollPane();
        fileDisplay.getStyleClass().add("scroll-pane");
        fileDisplay.setStyle("-fx-font-size: 16");
    }

    public Pane buildView(Item theItem){
        FlowPane mainPane = new FlowPane();
        mainPane.setAlignment(Pos.TOP_CENTER);

        filePane = new FlowPane();
        filePane.setAlignment(Pos.TOP_CENTER);
        filePane.setOrientation(Orientation.VERTICAL);
        filePane.setPrefWrapLength(10000000);
        headerPane = new GridPane();
        headerPane.setHgap(10);

        itemNameDisplay.setText(theItem.getName());
        descriptionField.setText(theItem.getDescription());
        descriptionField.setFont(fileButtonFont);

        buildFileViewer(theItem);

        Button insertDocument = insertDocButton(theItem);
        Button deleteDocument = deleteDocButton(theItem);

        headerPane.add(itemNameDisplay, 0, 0);
        headerPane.add(insertDocument, 1, 0);
        headerPane.add(deleteDocument, 2, 0);
        filePane.getChildren().add(headerPane);
        filePane.getChildren().add(fileDisplay);

        fileDisplay.setMaxHeight(myStage.getHeight() - 300);
        fileDisplay.setMinWidth(FILE_BUTTON_WIDTH);
        fileDisplay.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainPane.getChildren().addAll(filePane);//, descriptionField);
        return mainPane;
    }

    private void buildFileViewer(Item theItem) {
        itemFiles = theItem.getFiles();

        fileButtons = new Button[itemFiles.length];
        Arrays.sort(itemFiles, new Comparator<ItemFile>() {
            @Override
            public int compare(ItemFile file1, ItemFile file2) {
                return file1.getName().compareTo(file2.getName());
            }
        });

        //Desktop is used for opening files
        Desktop desktop = Desktop.getDesktop();

        for(int i = 0; i < itemFiles.length; i++){
            //Initializes the item button
            Button currFileButton = new Button("Error...");
            currFileButton.getStyleClass().add("transparent-square-button");
            currFileButton.setFont(fileButtonFont);
            currFileButton.setAlignment(Pos.CENTER_LEFT);

            File selectedFile = new File(itemFiles[i].getPath());
            currFileButton.setText(itemFiles[i].getName() + "\t : \t" + selectedFile.getName());
            currFileButton.setMinWidth(FILE_BUTTON_WIDTH);
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

        buttonGroup = new VBox(fileButtons);

        fileDisplay.setContent(buttonGroup);
    }

    private Button insertDocButton(Item theItem) {
        Button insertDocument = new Button();
        insertDocument.getStyleClass().add("squircle-button");
        insertDocument.setTooltip(new Tooltip("Insert a new document"));

        Image icon = new Image("/documentIcon.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(INS_DEL_BUTTON_WIDTH); iconView.setFitWidth(INS_DEL_BUTTON_WIDTH); //set the dimensions of the button
        insertDocument.setGraphic(iconView);

        insertDocument.setOnAction(clickEvent -> {
            int index = Database.db.getIndex(theItem);
            GUIController.guiControl.insertDocToItem(index);
        });
        return insertDocument;
    }

    private Button deleteDocButton(Item theItem) {
        Button deleteDocument = new Button();
        deleteDocument.getStyleClass().add("squircle-button");
        deleteDocument.setTooltip(new Tooltip("Delete an existing document"));

        Image icon = new Image("/deleteIcon.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(INS_DEL_BUTTON_WIDTH); iconView.setFitWidth(INS_DEL_BUTTON_WIDTH);
        deleteDocument.setGraphic(iconView);

        deleteDocument.setOnAction(clickEvent -> {
            switchFileButtons(true, theItem);
        });

        return deleteDocument;
    }

    /**
     * Switches between opening and deleting files
     */
    private void switchFileButtons(boolean deleting, Item theItem){

        Desktop desktop = Desktop.getDesktop();

        if(!deleting){
            for(int i = 0; i < itemFiles.length; i++){
                int index = i;

                File selectedFile = new File(itemFiles[i].getPath());

                fileButtons[i].setOnAction(clickEvent -> {
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
            }
        }else{
            for(int i = 0; i < itemFiles.length; i++){
                int index = i;
                fileButtons[i].setOnAction(clickEvent -> {
                    theItem.removeFile(itemFiles[index]);
                    buildFileViewer(theItem);
                });
            }
        }
    }

}
