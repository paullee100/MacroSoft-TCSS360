package main.GUI.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.Tab;
import main.data.Item;

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

    public InsertDocument(String buttonName, Image icon) {
        super(buttonName, icon);

        //sets up the text
        filePath = createText("File Path");
        obj = createText("Item");
        name = createText("Name");

        //sets up the text fields
        filePathBox = createField();

        objectBox = createItemDropDown();

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

    private ComboBox<Item> createItemDropDown(){
        String[] nameOfItem = {"DishWasher", "Wifi", "RentalDocuments", "SomeOtherGoodThings"};
        // test for a simple item list
        ArrayList<Item> items =  new ArrayList<Item>();
        for(int i = 0; i < nameOfItem.length; i++){
            Item temp = new Item(nameOfItem[i]);
            items.add(temp);
        }
        ObservableList<String> options = FXCollections.observableArrayList();
        Iterator<Item> itr = items.iterator();
        while(itr.hasNext()){
            options.add(itr.next().getName());

        }
        ComboBox temp = new ComboBox(options);
        temp.setMinSize(800, 60);
        return temp;
        
    }

}
