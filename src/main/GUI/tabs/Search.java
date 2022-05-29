package main.GUI.tabs;

/*
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.GUI.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Text;
*/
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.GUIController;
import main.GUI.Tab;

public class Search extends Tab {

    private static final int BUTTON_SIZE = 100;

    private final BorderPane borderPane;
    private final GridPane gridPane;
    private final Text Searching;
    private final TextField SearchingBox;
    private final Button MagGlass;
    public Search(String buttonName, Image icon) {

        super(buttonName, icon);
        borderPane = new BorderPane();
        gridPane = new GridPane();
        Searching = new Text("Search");
        Searching.getStyleClass().add("white-text");
        SearchingBox = new TextField();
        SearchingBox.setMinSize(100, 25);
        MagGlass = new Button();
        MagGlass.getStyleClass().add("transparent-square-button");
        ImageView Arrowimage = new ImageView(new Image("/searchIcon.png"));
        Arrowimage.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage.setFitWidth(BUTTON_SIZE * .40);
        MagGlass.setGraphic(Arrowimage);
    }


    @Override
    public Pane buildView(Stage stage) {
        gridPane.setMinSize(500,  250);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(MagGlass, 2, 0);
        gridPane.add(Searching, 0, 0);
        gridPane.add(SearchingBox, 1, 0);
        borderPane.setCenter(gridPane);
        borderPane.setMaxSize(500, 250);
        borderPane.setTop(createToolBar());
        return borderPane;
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
}
