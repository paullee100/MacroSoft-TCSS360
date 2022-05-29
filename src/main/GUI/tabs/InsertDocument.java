package main.GUI.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.GUI.Tab;

import static javafx.application.Application.launch;

public class InsertDocument extends Tab {

    private static final int BUTTON_SIZE = 100;

    private static final double SCALE = 2.5;

    private BorderPane borderPane;
    private GridPane gridPane;

    /** Text */
    private final Text filePath;
    private final Text obj;
    private final Text folder;
    private final Text name;

    /** Text fields */
    private final TextField filePathBox;
    private final TextField objectBox;
    private final TextField folderBox;
    private final TextField nameBox;

    /** Buttons */
    private final Button select;
    private final Button downArrow1;
    private final Button downArrow2;
    private final Button insertDoc;

    public InsertDocument(String buttonName, Image icon) {
        super(buttonName, icon);

        //sets up the text
        filePath = new Text("FilePath");
        filePath.getStyleClass().add("white-text");
        obj = new Text("Object");
        obj.getStyleClass().add("white-text");
        folder = new Text("Folder");
        folder.getStyleClass().add("white-text");
        name = new Text("Name");
        name.getStyleClass().add("white-text");

        //sets up the text fields
        filePathBox = new TextField();
        filePathBox.setMinSize(100 * SCALE, 25 * SCALE);
        filePathBox.getStyleClass().add("custom-text-entry");
        objectBox = new TextField();
        objectBox.setMinSize(100 * SCALE, 25 * SCALE);
        objectBox.getStyleClass().add("custom-text-entry");
        folderBox = new TextField();
        folderBox.setMinSize(100 * SCALE, 25 * SCALE);
        folderBox.getStyleClass().add("custom-text-entry");
        nameBox = new TextField();
        nameBox.setMinSize(100 * SCALE, 25 * SCALE);
        nameBox.getStyleClass().add("custom-text-entry");

        //sets up the buttons
        select = new Button("Select");
        select.getStyleClass().add("squircle-button");
        downArrow1 = new Button();
        downArrow1.getStyleClass().add("transparent-square-button");
        downArrow1.setScaleX(SCALE/2);
        downArrow1.setScaleY(SCALE/2);
        downArrow2 = new Button();
        downArrow2.getStyleClass().add("transparent-square-button");
        downArrow2.setScaleX(SCALE/2);
        downArrow2.setScaleY(SCALE/2);
        ImageView Arrowimage = new ImageView(new Image("/insert.png"));
        ImageView Arrowimage2 = new ImageView(new Image("/insert.png"));
        Arrowimage.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage.setFitWidth(BUTTON_SIZE * .40);
        Arrowimage2.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage2.setFitWidth(BUTTON_SIZE * .40);
        downArrow1.setGraphic(Arrowimage);
        downArrow2.setGraphic(Arrowimage2);
        insertDoc = new Button("Insert Document");
        insertDoc.setPrefSize(300 * SCALE, 25 * SCALE);
        insertDoc.getStyleClass().add("squircle-button");
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
        gridPane.add(folder, 0, 2);
        gridPane.add(name, 0, 3);
        gridPane.add(filePathBox, 1, 0);
        gridPane.add(objectBox, 1, 1);
        gridPane.add(folderBox,1, 2);
        gridPane.add(nameBox, 1, 3);
        gridPane.add(downArrow1, 2, 1);
        gridPane.add(downArrow2, 2, 2);
        gridPane.add(insertDoc, 1, 4);
        gridPane.add(select, 2, 0);
        borderPane.setCenter(gridPane);
        //borderPane.setMaxSize(500, 250);
        //borderPane.setTop(createToolBar());
        return borderPane;
    }

    private void clearTextFields() {
        filePathBox.clear();
        objectBox.clear();
        folderBox.clear();
        nameBox.clear();
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

}
