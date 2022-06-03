package main.GUI.tabs;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.Tab;
import main.data.Database;
import main.data.Item;
import main.data.ItemFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A class to make the Search GUI and search the database for
 * the item or file the user is searching for.
 *
 *
 * @author Wei Wei Chien
 * @version Spring 2022
 */

public class Search extends Tab {

    /**
     * Default size of buttons
     */
    private static final int BUTTON_SIZE = 100;

    /**
     * Overall border pane of the GUI
     */
    private BorderPane borderPane;

    /**
     * Grid pane that contains GUI elements
     */
    private GridPane gridPane;

    /**
     * Array of Items
     */
    private String[] itemsListData = {"Remote", "Example1", "Example2"};
    private Item[] items;
    private Item[] itemsList;
    private Item[] filesList;

    /**
     * Text to show Search
     */
    private final Text selectText;
    private final Text searchingText;
    private final Text errormsg;
    /**
     * Drop down box
     */
    private ChoiceBox<String> choiceBox;

    /**
     * Text field that allows user to search
     */
    private final TextField searchingBox;

    /**
     * Buttons
     */
    private final Button magGlass;

    //private final Button selectButton;

    /**
     * Constructor that class the super method from the tabs class.
     * Builds the GUI for Search. Includes Text, user input field, and
     * Search button.
     *
     * @param buttonName name of the button - Search
     * @param icon image of the button
     */
    public Search(String buttonName, Image icon) {

        super(buttonName, icon);

        //Text to prompt which type of search
        selectText = new Text("Select");
        selectText.getStyleClass().add("white-text");


        //Dropdown box for user to choose type
        choiceBox = new ChoiceBox<>();

        choiceBox.getItems().add("Items");
        choiceBox.getItems().add("Files");
        //default dropdown box setting
        choiceBox.setValue("Items");

        String choice = choiceBox.getValue();

//        selectButton = new Button("Select");
//
//        selectButton.setOnAction(e -> getChoice(choiceBox));


        //Text to prompt which type of search
        searchingText = new Text("Searching");
        searchingText.getStyleClass().add("white-text");

        //Text field for the user to type what they're searching
        searchingBox = new TextField();
        searchingBox.setMinSize(100, 25);

        //Sets up the Search button
        magGlass = new Button();
        magGlass.getStyleClass().add("transparent-square-button");
        ImageView Arrowimage = new ImageView(new Image("/searchIcon.png"));
        Arrowimage.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage.setFitWidth(BUTTON_SIZE * .40);
        magGlass.setGraphic(Arrowimage);

        //get what user is searching for
        final String[] userSearching = new String[1];
        magGlass.setOnAction(e -> {
            userSearching[0] = searchingBox.getText();
        });
        String userSearch = userSearching[0];

        //Depending on what user choose, different database
//        if (selectButton.equals("Items")) {
//            String item = searchingBox.getText();
//            searchItem(item);
//        } else if (selectButton.equals("Files")) {
//            String file = searchingBox.getText();
//            searchFile(file);
//        }

        if (choice.equals("Items")) {
            String item = searchingBox.getText();
            searchItem(userSearch);
        } else { //if (choice.equals("Files"))
            String file = searchingBox.getText();
            searchFile(userSearch);
        }


        //sets up error message
        javafx.scene.text.Font font = Font.font("Verdana", FontWeight.BOLD, 25);
        errormsg = new Text("This does not exist!");
        errormsg.setFill(Color.RED);
        errormsg.setVisible(false);
        errormsg.setFont(font);
    }

    /**
     * Builds the GUI for Search. Aligns and size everything in pane.
     *
     * @param stage to add in GUI
     */
    @Override
    public Pane buildView(Stage stage) {
        //create pane
        borderPane = new BorderPane();
        gridPane = new GridPane();

        //set size and align elements
        gridPane.setMinSize(500,  250);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(selectText, 0, 0);
        gridPane.add(choiceBox, 1, 0);

        gridPane.add(searchingText, 0, 2);
        gridPane.add(searchingBox, 1, 2);
        gridPane.add(magGlass, 2, 2);
        gridPane.add(errormsg, 1, 4);
        borderPane.setCenter(gridPane);
        borderPane.setMaxSize(500, 250);

        return borderPane;
    }


    /**
     * Searching Items(database)
     */
    public Item[] searchItem(String Item) {
        items = Database.db.getItems();
        for (int i = 0; i < items.length; i++) {
            //if have searched term, add to list
            if (items[i].equals(Item)) {
                itemsList[i] = items[i];
            }
        }

        //should be able to search with shorter characters
//        for (int i = 0; i < items.length; i++) {
//            //if have searched term, add to list
//            if (items[i].indexOf(Item)) {
//                itemsList[i] = items[i];
//            }
//        }

          //error message if nothing matched
//        if (itemsList == null) {
//            errormsg.setVisible(true);
//        }
//        if (itemsList.length < 1) {
//            errormsg.setVisible(true);
//        }
        return itemsList;
    }

    /**
     * Searching Files
     */
    public Item[] searchFile(String File) {
        //items.getFiles();
        items = Database.db.getItems();
        for (int i = 0; i < items.length; i++) {
            items[i].getFiles();
            //if have searched term, add to list
            if ((items[i].getName()).equals(File)) {
                itemsList[i] = items[i];
            }

        }
//        if (itemsList.length == 0) {
//            errormsg.setVisible(true);
//        }

        return itemsList;
    }

    /**
     * Files display
     */
    public void displayView() {
//        mainPane = new GridPane();
//        mainPane.setAlignment(Pos.TOP_CENTER);
//
//        itemNameDisplay.setText(theItem.getName());
//
//        ItemFile[] theFiles = theItem.getFiles();
//
//        fileButtons = new Button[theFiles.length];
//
//        //Desktop is used for opening files
//        Desktop desktop = Desktop.getDesktop();
//
//        for(int i = 0; i < theFiles.length; i++){
//            //Initializes the item button
//            Button currFileButton = new Button("Error...");
//            currFileButton.getStyleClass().add("transparent-square-button");
//            currFileButton.setFont(fileButtonFont);
//
//
//            File selectedFile = new File(theFiles[i].getPath());
//            currFileButton.setText(selectedFile.getName());
//            int index = i;//action listeners can't take in i
//            currFileButton.setOnAction(clickEvent -> {
//                //Gets the file from the path
//                //Makes sure the file exists
//                if(selectedFile.exists()){
//                    try {
//                        desktop.open(selectedFile);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else{
//                    System.out.println("Error: file does not exist");
//                }
//            });
//            fileButtons[i] = currFileButton;
//        }
//
//        mainPane.add(itemNameDisplay, 0, 0);
//        mainPane.add(fileDisplay, 0, 1);
//        fileDisplay.setAlignment(Pos.CENTER);
//        fileDisplay.getChildren().addAll(fileButtons);
//        return mainPane;
    }


    public HBox createToolBar() {
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

    /**
     * getting the user choice of file or item
     * @param choiceBox
     * @return type - user choice of file/item
     */
    private String getChoice(ChoiceBox<String> choiceBox) {
        String type = choiceBox.getValue();
        return type;
    }

}
