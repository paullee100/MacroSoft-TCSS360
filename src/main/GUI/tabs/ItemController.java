package main.GUI.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.Tab;
import main.data.Database;

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

 //   private Database myDataBase;

    private Button[] itemButtons;

    private BorderPane borderPane;

    private Text titleText;

    /**
     * Constructor that calls the super method from the tabs class.
     *
     * @param buttonName name of the button.
     * @param icon image of the button.
     */
    public ItemController(String buttonName, Image icon) {
        super(buttonName, icon);
        Database.db.createItem("TV");
        Database.db.createItem("Remote");

        for (int i = 0; i < 7; i++) {
            Database.db.createItem("Example" + i);
        }
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
        titleText = new Text("MacroSoft360's Program");
        titleText.setFill(Color.WHITE);
        titleText.setFont(Font.font("verdana", FontWeight.BOLD, 50));
        borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.getStyleClass().add("toolbar");
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(5));

        Button delete = new Button();
        createButton(delete, "Delete", "/deleteIcon.png");
        delete.setOnAction(e -> delete()); // Dummy call

        Button createItem = new Button();
        createButton(createItem, "Create", "/createItemIcon.png");
        createItem.setOnAction(e -> create()); // Dummy call

        hBox.getChildren().addAll(titleText, delete, createItem);
        borderPane.setTop(hBox);
        createItemGUI(borderPane);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        return borderPane;
    }

    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(500, 500);
        scrollPane.getStylesheets().add("/Stylesheet.css");

        return scrollPane;
    }
    /**
     * Creates each of the button of the GUI.
     *
     * @param button the button to adjust the size and to place a graphic.
     * @param name the name of the button.
     * @param imagePath the path of the image to use.
     * @return a completed sized and graphic button.
     */
    private Button createButton(Button button, String name, String imagePath) {
        button.getStyleClass().add("squircle-button");
        button.getStyleClass().add("transparent-square-button");
        button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        button.setTooltip(new Tooltip(name));
        button.setGraphic(addGraphic(new Image(imagePath)));
        return button;
    }

    /**
     * Sets up the image to be applied to the button.
     *
     * @param icon the image to be applied.
     * @return the image ready to be applied to a button.
     */
    private ImageView addGraphic(Image icon) {
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(BUTTON_SIZE * .65);
        iconView.setFitWidth(BUTTON_SIZE * .65);

        return iconView;
    }

    /**
     * Creates and sets up the GUI for the items, which would be placed
     * in the center of the GUI.
     *
     * @param borderPane to place the buttons on.
     * @return the BorderPane of buttons.
     */
    private Pane createItemGUI(BorderPane borderPane) {
        itemButtons = new Button[Database.db.getItems().length];
        int row = 0; // Keeps track of which row the buttons will appear on.
        int column = 0; // Keeps track of which column the buttons will appear on.

        GridPane paneOfItems = new GridPane();
        paneOfItems.setPadding(new Insets(10));
        paneOfItems.setHgap(10);
        paneOfItems.setVgap(10);
        for (int i = 0; i < Database.db.getItems().length; i++) {
            itemButtons[i] = new Button(Database.db.getItems()[i].getName());
            itemButtons[i].setPrefSize(BUTTON_SIZE + 0.5 * BUTTON_SIZE, BUTTON_SIZE + 0.5 * BUTTON_SIZE);
            if (i % 8 == 0) {
                row++; // If there are 8 buttons on a single row, move to next row.
                column = 0; // The column will start at the start in the new row.
            }
            paneOfItems.add(itemButtons[i], column++, row);
        }
        createScrollPane().setContent(paneOfItems);
        borderPane.setCenter(paneOfItems);
        return borderPane;
    }

    /**
     * Method to allow the user to delete an item
     */
    private void delete() {
        Alert confirmToDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmToDelete.setTitle("Confirmation");
        ButtonType confirmButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

        titleText.setText("Click on an item to delete.");
        for (int i = 0; i < Database.db.getItems().length; i++) {
            int index = i; // index is needed since I can't place "i" inside the lambda expression
                           // Since it has to be a final or effectively final variable to work.
            itemButtons[i].setOnAction(e -> {
                Database.db.removeItem(index);
                createItemGUI(borderPane); // Calls back to update the GUI.
                titleText.setText("MacroSoft360's Program");
            });
        }
    }

    /**
     * Method to allow the user to create a new item.
     * Duplicate names are not allowed.
     */
    private void create() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Create Item");
        alert.getDialogPane().getStylesheets().add("/Stylesheet.css");
        titleText.setText("Creating an item...");

        GridPane gridPane = new GridPane();

        Text text = new Text("Item Name: ");
        TextField textField = new TextField();
        gridPane.add(text, 0, 0);
        gridPane.add(textField, 0, 1);

        alert.getDialogPane().setContent(gridPane);
        ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getDialogPane().getButtonTypes().add(confirm);
        alert.getDialogPane().getButtonTypes().add(cancel);

        alert.showAndWait().ifPresent(type -> {
            if (type == confirm) {
                Database.db.createItem(textField.getText());
                createItemGUI(borderPane);
                titleText.setText("MacroSoft360's Program");
            } else {
                titleText.setText("MacroSoft360's Program");
                return; // The user clicked on the cancel button, so no items are deleted.
            }
        });
    }
}
