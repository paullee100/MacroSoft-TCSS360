package main.GUI.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.GUI.Tab;

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

    /**
     * Constructor that calls the super method from the tabs class.
     * @param buttonName name of the button.
     * @param icon image of the button.
     */
    public ItemController(String buttonName, Image icon) {
        super(buttonName, icon);
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
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.getStyleClass().add("toolbar");
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(5));

        Button filter = new Button();
        filter.getStyleClass().add("transparent-square-button");
        filter.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        filter.setTooltip(new Tooltip("Filter"));
        filter.setGraphic(addGraphic(new Image("/filterIcon.png")));
        filter.setOnAction(e -> filter()); // Dummy call

        Button delete = new Button();
        delete.getStyleClass().add("transparent-square-button");
        delete.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        delete.setTooltip(new Tooltip("Delete"));
        delete.setGraphic(addGraphic(new Image("/deleteIcon.png")));
        delete.setOnAction(e -> delete()); // Dummy call

        Button createItem = new Button();
        createItem.getStyleClass().add("transparent-square-button");
        createItem.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        createItem.setTooltip(new Tooltip("Delete"));
        createItem.setGraphic(addGraphic(new Image("/createItemIcon.png")));
        createItem.setOnAction(e -> create()); // Dummy call

        hBox.getChildren().addAll(filter, delete, createItem);

        borderPane.setTop(hBox);
        borderPane.setCenter(itemsGUI());
        hBox.setAlignment(Pos.CENTER_RIGHT);


        return borderPane;
    }

    private ImageView addGraphic(Image icon) {
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(BUTTON_SIZE * .65);
        iconView.setFitWidth(BUTTON_SIZE * .65);

        return iconView;
    }

    /**
     * A sample of how the GUI would look with items.
     *
     * @return GridPane of the items
     */
    private Pane itemsGUI() {
        Button[] butt = new Button[5];
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < butt.length; i++) {
            butt[i] = new Button("Button " + i);
            butt[i].setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
            gridPane.add(butt[i], i, 0);
        }

        return gridPane;
    }

    /**
     * For future implementation of the filter button.
     */
    private void filter() {
        System.out.println("Filter");
        return;
    }

    /**
     * For future implementation of the delete button.
     */
    private void delete() {
        System.out.println("Delete");
        return;
    }

    /**
     * For future implementation of the create button.
     */
    private void create() {
        System.out.println("Create");
        return;
    }
}
