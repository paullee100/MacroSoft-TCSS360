package main.GUI.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.Tab;
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
    private String[] itemsList;
    private String[] filesList;

    /**
     * Text to show Search
     */
    private final Text Searching;
    /**
     * Text field that allows user to search
     */
    private final TextField SearchingBox;

    /**
     * Buttons
     */
    private final Button MagGlass;

    private final Button Select = null;

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
        //Selecting = new Text("Select");

        //Dropdown box for user to choose type
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        choiceBox.getItems().add("Items");
        choiceBox.getItems().add("Files");

        Select.setOnAction(e -> getChoice(choiceBox));

        //Text to prompt which type of search
        Searching = new Text("Searching");

        //Text field for the user to type what they're searching
        Searching.getStyleClass().add("white-text");
        SearchingBox = new TextField();
        SearchingBox.setMinSize(100, 25);

        //Sets up the Search button
        MagGlass = new Button();
        MagGlass.getStyleClass().add("transparent-square-button");
        ImageView Arrowimage = new ImageView(new Image("/searchIcon.png"));
        Arrowimage.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage.setFitWidth(BUTTON_SIZE * .40);
        MagGlass.setGraphic(Arrowimage);

        //Depending on what user choose, different database
        if (Select.equals("Items")) {
            String item = SearchingBox.getText();
            searchItem(item);
        } else if (Select.equals("Files")) {
            String file = SearchingBox.getText();
            searchFile(file);
        }
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
        gridPane.add(MagGlass, 2, 0);
        gridPane.add(Searching, 0, 0);
        gridPane.add(SearchingBox, 1, 0);
        borderPane.setCenter(gridPane);
        borderPane.setMaxSize(500, 250);
        //borderPane.setTop(createToolBar());
        return borderPane;
    }


    /**
     * Searching Items(database)
     */
    public String[] searchItem(String Item) {
        //items = Database.db.getItems();
        for (int i = 0; i < itemsListData.length; i++) {
            //if have searched term, add to list
            if (itemsListData[i].equals(Item)) {
                itemsList[i] = itemsListData[i];
            }
        }
        return itemsList;
    }

    /**
     * Searching Files
     */
    public String[] searchFile(String File) {
        //items.getFiles();
        for (int i = 0; i < itemsListData.length; i++) {
            //if have searched term, add to list
            if (itemsListData[i].equals(File)) {
                filesList[i] = itemsListData[i];
            }
        }
        return filesList;
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
