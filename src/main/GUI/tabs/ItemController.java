package main.GUI.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.ItemDisplay;
import main.GUI.Tab;
import main.data.Database;
import main.data.Item;
import main.data.ItemFile;

/**
 * ItemController Class that will control the items,
 * which is shown to the user when they click on
 * the "home" button in the left side of the
 * program.
 *
 * @author Gabriel Bryan, Paul Lee
 * @version Spring 2022
 */
public class ItemController extends Tab {

    /** Sets the size of the button */
    private static final int BUTTON_SIZE = 100;

    /** When a certain action requires user input/confirmation */
    private Alert alert;

    /** Pane for the buttons to be in */
    private BorderPane buttonPane;

    /** The numbers of buttons for the item(s) */
    private Button[] itemButtons;

    /** The button that deletes an item */
    private Button deleteButton;

    /** The button that creates an item */
    private Button createButton;

    /** The text that appears on top of the borderpane */
    private Text titleText;

    private Stage myStage;

    /**
     * Constructor that calls the super method from the tabs class.
     *
     * @param buttonName name of the button.
     * @param icon image of the button.
     */
    public ItemController(String buttonName, Image icon) {
        super(buttonName, icon);
        /*
        Database.db.createItem("TV");
        Database.db.createItem("Computers");

        // Itemfiles are for demonstration purposes only to showcase how the application works.
        ItemFile itemFile = new ItemFile("Samsung TV Manual", Database.db.getWorkingDirectory() + "\\SamsungTVManual.pdf");
        ItemFile itemFile2 = new ItemFile("Samsung LED TV Manual", Database.db.getWorkingDirectory() + "\\SamsungLEDTVManual.pdf");

        ItemFile itemFile3 = new ItemFile("Apple II Manual", Database.db.getWorkingDirectory() + "\\AppleIIManual.pdf");
        ItemFile itemFile4 = new ItemFile("Windows 95 Manual", Database.db.getWorkingDirectory() + "\\Windows 95 Manual.pdf");

        Database.db.getItems()[0].addFile(itemFile);
        Database.db.getItems()[0].addFile(itemFile2);

        Database.db.getItems()[1].addFile(itemFile3);
        Database.db.getItems()[1].addFile(itemFile4);
        */
    }

    /**
     * Builds the GUI to include the filter, delete, and create buttons
     * on the top right of the screen and the items that will be in the
     * center.
     *
     * @param stage to add in the additional GUI
     * @return the BorderPane of the buttons.
     */
    @Override
    public Pane buildView(Stage stage) {
        // Added spaces in the titleText to appear correctly in the GUI.
        titleText = new Text("MacroSoft360's Program                              ");
        titleText.getStyleClass().add("white-text");
        titleText.setFont(Font.font("verdana", FontWeight.BOLD, 50));

        buttonPane = new BorderPane();
        HBox topBar = new HBox();
        topBar.setSpacing(10);
        topBar.setPadding(new Insets(5));

        Background background = new Background(new BackgroundFill(Color.rgb(40,43,56), CornerRadii.EMPTY, Insets.EMPTY));
        topBar.setBackground(background);

        deleteButton = createButton("Delete", "/deleteIcon.png");
        deleteButton.setOnAction(e -> deleteAItem(stage));

        createButton = createButton("Create", "/createItemIcon.png");
        createButton.setOnAction(e -> createAItem(stage));

        topBar.getChildren().addAll(titleText, deleteButton, createButton);
        buttonPane.setTop(topBar);
        createItemGUI(buttonPane);
        topBar.setAlignment(Pos.CENTER_RIGHT);

        testItemDisplay(stage);

        if(myStage == null) myStage = stage;

        return buttonPane;
    }

    /**
     * A demonstration of how looking inside the item would look like.
     *
     * @param stage the stage to be changed to display the files.
     */
    private void testItemDisplay(Stage stage) {
        ItemDisplay newItemDisplay = new ItemDisplay(stage);
        //itemButtons[0].setOnAction(e -> buttonPane.setCenter(newItemDisplay.buildView(Database.db.getItems()[0])));
        //itemButtons[1].setOnAction(e -> buttonPane.setCenter(newItemDisplay.buildView(Database.db.getItems()[1])));
        for (int i = 0; i < itemButtons.length; i ++) {
            int finalI = i;
            itemButtons[i].setOnAction(e -> buttonPane.setCenter(newItemDisplay.buildView(Database.db.getItems()[finalI])));
        }
    }

    /**
     * Creates each of the button of the GUI.
     *
     * @param buttonName the name of the button.
     * @param imagePath the path of the image to use.
     * @return a completed sized and graphic button.
     */
    private Button createButton(String buttonName, String imagePath) {
        Button topButton = new Button();
        topButton.getStyleClass().add("transparent-square-button");
        topButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        topButton.setTooltip(new Tooltip(buttonName));

        ImageView iconView = new ImageView(new Image(imagePath));
        iconView.setFitHeight(BUTTON_SIZE * .65);
        iconView.setFitWidth(BUTTON_SIZE * .65);
        topButton.setGraphic(iconView);

        return topButton;
    }

    /**
     * Creates and sets up the GUI for the items, which would be placed
     * in the center of the GUI. The items are placed inside a scroll pane
     * so that if there are too many items to fit into one screen, the user
     * can scroll up and down for the other items.
     *
     * @param borderPane to place the buttons on.
     */
    private void createItemGUI(BorderPane borderPane) {
        itemButtons = new Button[Database.db.getItems().length];
        int row = 0; // Keeps track of which row the buttons will appear on.
        int column = 0; // Keeps track of which column the buttons will appear on.

        GridPane paneOfItems = new GridPane();
        paneOfItems.setPadding(new Insets(10));
        paneOfItems.setHgap(10);
        paneOfItems.setVgap(10);
        for (int i = 0; i < Database.db.getItems().length; i++) {
            itemButtons[i] = new Button(Database.db.getItems()[i].getName());
            itemButtons[i] = createButton(Database.db.getItems()[i].getName(), "/folderIcon.png");
            itemButtons[i].setText(Database.db.getItems()[i].getName());
            itemButtons[i].setPrefSize(BUTTON_SIZE + 0.5 * BUTTON_SIZE, BUTTON_SIZE + 0.5 * BUTTON_SIZE);
            if (i % 8 == 0) {
                row++; // If there are 8 buttons on a single row, move to next row.
                column = 0; // The column will start at the start in the new row.
            }
            paneOfItems.add(itemButtons[i], column++, row);
        }

        // Items are placed in a scroll pane and the user can scroll through the items
        // if there are many items. This is to prevent the items to be squished together.
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(scrollPane.getWidth(), 12000); // 12000 is an arbitrary number.
        scrollPane.setPannable(true);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Only wants the user to scroll up and down.
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Scroll bar will only appear if there are

        scrollPane.setContent(paneOfItems);
        borderPane.setCenter(scrollPane);
    }

    /**
     * Method to allow the user to delete an item
     */
    private void deleteAItem(Stage stage) {
        // Added spaces in the titleText to appear correctly in the GUI.
        titleText.setText("Select an item to delete:                              ");
        for (int i = 0; i < Database.db.getItems().length; i++) {
            int index = i; // index is needed since "i" can't be used inside the lambda expression
                           // Since it has to be a final or effectively final variable to work.
            itemButtons[i].setOnAction(e -> confirmToDelete(index, stage));
        }
    }

    /**
     * When the user clicks on an item to delete, they will be
     * prompt to confirm their delete.
     * Yes indicates deleting the item
     * No indicates that no item will be deleted.
     *
     * @param index the index of the item in the arraylist.
     */
    private void confirmToDelete(int index, Stage stage) {
        BorderPane alertBox = new BorderPane();

        Text confirm = new Text("Are you sure you want to delete " + Database.db.getItems()[index].getName() + "?");
        confirm.getStyleClass().add("white-text");

        alertBox.setCenter(confirm);

        alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmation");
        alert.getDialogPane().getStylesheets().add("/Stylesheet.css");
        alert.getDialogPane().setContent(alertBox);

        ButtonType confirmButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getDialogPane().getButtonTypes().add(confirmButton);
        alert.getDialogPane().getButtonTypes().add(noButton);

        alert.showAndWait().ifPresent(type -> {
            if (type == confirmButton) {
                Database.db.removeItem(index);
                createItemGUI(buttonPane); // Calls back to update the GUI.
                // Added spaces in the titleText to appear correctly in the GUI.
                titleText.setText("MacroSoft360's Program                              ");
            } else {
                // User selected the "no" button.
                // Added spaces in the titleText to appear correctly in the GUI.
                titleText.setText("MacroSoft360's Program                              ");
            }
            testItemDisplay(myStage);
        });
    }

    /**
     * Method to allow the user to create a new item.
     * Duplicate names are not allowed.
     */
    private void createAItem(Stage stage) {
        // Added spaces in the titleText to appear correctly in the GUI.
        titleText.setText("Creating an item...                                               ");

        Text dupeDisplay = new Text();
        dupeDisplay.setFill(Color.RED);

        Text itemNameText = new Text("Type a name for the item: ");
        itemNameText.getStyleClass().add("white-text");

        TextField userInputName = new TextField();
        userInputName.getStyleClass().add("custom-text-entry");

        GridPane alertPane = new GridPane(); // GridPane to be used in the alert box
        alertPane.add(itemNameText, 0, 0);
        alertPane.add(userInputName, 0, 1);
        alertPane.add(dupeDisplay, 0, 2);

        createItemAlertBox(dupeDisplay, userInputName, alertPane);

    }

    /**
     * Alert box for when the user is creating an item.
     *
     * @param invalidName text that appears when the user inputs an item name that already exists.
     * @param userInputName text that displays to the user to input the name of the item.
     * @param alertPane the pane for the alert box.
     */
    private void createItemAlertBox(Text invalidName, TextField userInputName, GridPane alertPane) {
        alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Create Item");
        alert.getDialogPane().getStylesheets().add("/Stylesheet.css");

        alert.getDialogPane().setContent(alertPane);
        ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getDialogPane().getButtonTypes().add(confirm);
        alert.getDialogPane().getButtonTypes().add(cancel);

        alert.showAndWait().ifPresent(type -> {
            while (checkNameDupe(userInputName.getText())) {
                invalidName.setText("Item with same name already exists");
                alert.showAndWait();

                if (type == cancel) {
                    // Added spaces in the titleText to appear correctly in the GUI.
                    titleText.setText("MacroSoft360's Program                              ");
                    return;
                }
            }

            if (type == confirm) {
                Database.db.createItem(userInputName.getText());
                createItemGUI(buttonPane);
                // Added spaces in the titleText to appear correctly in the GUI.
                titleText.setText("MacroSoft360's Program                              ");
            } else {
                // The user selected the cancel button, so no items are deleted.
                // Added spaces in the titleText to appear correctly in the GUI.
                titleText.setText("MacroSoft360's Program                              ");
            }
            testItemDisplay(myStage);
        });
    }

    /**
     * Used as part of the createAItem method to check if there are
     * already an item with that name in the Database. If so, this method
     * will return true indicating that the name already exists in the Database,
     * false otherwise.
     *
     * @return true if name already exists, false otherwise.
     */
    private boolean checkNameDupe(String newItemName) {
        return Database.db.hasItem(newItemName);
    }

    private HBox createToolBar(String theTitle) {
        HBox toolBar = new HBox();
        Text title = new Text(theTitle);
        title.setX(0);

        toolBar.getChildren().add(title);

        toolBar.getStyleClass().add("toolbar");
        toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setSpacing(10);
        toolBar.setPadding(new Insets(5));

        Button close = new Button(" x ");
        close.getStyleClass().add("close-button");
        close.setOnAction(e -> alert.close());

        return toolBar;
    }
}
