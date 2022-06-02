package main.GUI.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.GUI.Tab;
import main.data.Database;
import main.data.Item;
import main.data.ItemFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static javafx.application.Application.launch;

/**
 * ItemController Class that will control the function of
 * Inserting document, which is shown to the user when they
 * click on the "insert" button on the left side of the
 * program.
 *
 * @author Gabriel Bryan, Anteh Hsu
 * @version Spring 2022
 */

public class InsertDocument extends Tab {

    /**
     * Default size of buttons
     */
    private static final int BUTTON_SIZE = 100;

    /**
     * default scaling variables
     */
    private static final double SCALE = 2.5;

    /**
     * default font size
     */
    private static final double FONT_SIZE = 16;

    /**
     * Overall border pane of the GUI
     */
    private BorderPane borderPane;

    /**
     * Grid pane that contains GUI elements
     */
    private GridPane gridPane;

    /** Text */
    private final Text filePath;
    private final Text obj;
    private final Text name;

    /** Text fields */
    private final TextField filePathBox;
    private final ComboBox objectBox;
    private final TextField nameBox;

    /** Buttons */
    private final Button select;
    private final Button insertDoc;

    /** String for filepath, item and name */
    private String pathStr = "";

    private Item selectItem = null;

    private String nameStr = "";

    private File myFile;

    private final Text errormsg;

    private final Text SuccessMsg;


    public InsertDocument(String buttonName, Image icon) {
        super(buttonName, icon);

        //sets up the text
        filePath = createText("File Path");
        obj = createText("Item");
        name = createText("Name");

        //sets up the text fields
        filePathBox = createField();

        //sample items setup
        String[] nameOfItem = {"DishWasher", "Wifi", "RentalDocuments", "SomeOtherGoodThings"};
        // test for a simple item list
        ArrayList<Item> items =  new ArrayList<Item>();
        for(int i = 0; i < nameOfItem.length; i++){
            Item temp = new Item(Database.db, nameOfItem[i]);
            items.add(temp);
        }

        objectBox = createItemDropDown(items);
        objectBox.setOnAction( e -> {
            System.out.println(objectBox.getValue().getClass());
        });

        nameBox = createField();

        //sets up the select button
        select = new Button("Select");
        select.getStyleClass().add("squircle-button");
        select.setFont(new Font(FONT_SIZE));



        //sets up the insert document button
        insertDoc = new Button("Insert Document");
        insertDoc.setPrefSize(300 * SCALE, 25 * SCALE);
        insertDoc.getStyleClass().add("squircle-button");
        insertDoc.setFont(new Font(FONT_SIZE));
        addButtonListener();
        errormsg = new Text("All fields need to be filled before inserting!");
        errormsg.setFill(Color.RED);
        errormsg.setVisible(false);
        SuccessMsg = new Text("Successfully inserting Document.");
        SuccessMsg.setFill(Color.GREEN);
        SuccessMsg.setVisible(false);
    }

    @Override
    public Pane buildView(Stage stage) {
        clearTextFields();

        borderPane = new BorderPane();
        gridPane = new GridPane();

        gridPane.setPrefSize(stage.getWidth()/2,  stage.getMaxHeight()/2);
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(filePath, 0, 0);
        gridPane.add(obj, 0, 1);
        gridPane.add(name, 0, 3);
        gridPane.add(filePathBox, 1, 0);
        gridPane.add(objectBox, 1, 1);
        gridPane.add(nameBox, 1, 3);
        gridPane.add(insertDoc, 1, 4);
        gridPane.add(select, 2, 0);
        gridPane.add(errormsg, 1, 5);
        gridPane.add(SuccessMsg,1,5);
        borderPane.setCenter(gridPane);
        //borderPane.setMaxSize(500, 250);
        //borderPane.setTop(createToolBar());
        return borderPane;
    }

    private void clearTextFields() {
        filePathBox.clear();
        nameBox.clear();
    }

    private TextField createField() {
        TextField theField = new TextField();
        theField.setMinSize(100 * SCALE, 25 * SCALE);
        theField.getStyleClass().add("custom-text-entry");
        theField.setFont(new Font(FONT_SIZE));
        return theField;
    }

    private Text createText(String text) {
        Text theText = new Text(text);
        theText.getStyleClass().add("white-text");
        theText.setFont(new Font(FONT_SIZE));
        return theText;
    }

    private Button createIconButton(ImageView icon, double scale) {
        Button theButton = new Button();
        theButton.getStyleClass().add("transparent-square-button");

        theButton.setScaleX(scale);
        theButton.setScaleY(scale);
        theButton.setGraphic(icon);

        return theButton;
    }

    private HBox createToolBar() {
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("toolbar");
        toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setSpacing(10);
        toolBar.setPadding(new Insets(5));


        //Creates the close button
        Button close = new Button(" x ");
        close.getStyleClass().add("close-button");
        close.setOnAction(e -> {
            borderPane.setVisible(false);
        });
        toolBar.getChildren().add(close);

        return toolBar;
    }

    private ComboBox<Item> createItemDropDown(ArrayList<Item> items){

        ObservableList<Item> options = FXCollections.observableArrayList();
        Item[] list = Database.db.getItems();
        for(int i = 0; i < list.length; i++){
            options.add(list[i]);
        }
        ComboBox temp = new ComboBox(options);
        temp.getStyleClass().add("combo-box");

        temp.setMinSize(800, 60);
        return temp;
        
    }

    private void addButtonListener(){

        select.setOnAction(e -> {
            Stage temp = new Stage();
            FileChooser chooser = new FileChooser();
            myFile = chooser.showOpenDialog(temp);
            if(myFile.exists() && myFile.isFile()){
                errormsg.setVisible(false);
                filePathBox.setText(myFile.getPath());
                System.out.println(filePathBox.getText());
            } else {
                errormsg.setText("Invalid file path or File does not exist!");
                errormsg.setVisible(true);
            }

            }
        );

        insertDoc.setOnAction(e -> {
            myFile = new File(filePathBox.getText());
            if(!myFile.exists()){
                errormsg.setText("File does not exist!");
                SuccessMsg.setVisible(false);
                errormsg.setVisible(true);
            }
            else if(filePathBox.getText().equals("") || objectBox.getValue() == null || nameBox.getText().equals("")){
                SuccessMsg.setVisible(false);
                errormsg.setVisible(true);
            } else{
                errormsg.setVisible(false);
                errormsg.setText("All fields need to be filled before inserting!");
                pathStr = filePathBox.getText();
                selectItem = (Item) objectBox.getValue();
                nameStr = nameBox.getText();
                ItemFile doc = new ItemFile(nameStr, pathStr);
                selectItem.addFile(doc);
                SuccessMsg.setVisible(true);
                objectBox.setValue(null);
                System.out.println(Database.db.getItems());
                
            }

            }
        );
    }




}
