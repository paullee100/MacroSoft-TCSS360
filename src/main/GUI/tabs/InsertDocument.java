package main.GUI.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
        filePath = createText("FilePath");
        obj = createText("Object");
        folder = createText("Folder");
        name = createText("Name");

        //sets up the text fields
        filePathBox = createField();
        objectBox = createField();
        folderBox = createField();
        nameBox = createField();

        //sets up the select button
        select = new Button("Select");
        select.getStyleClass().add("squircle-button");
        select.setFont(new Font(16));

        //sets the image for the down arrows
        ImageView arrowIcon = new ImageView(new Image("/insert.png"));
        arrowIcon.setFitHeight(BUTTON_SIZE * .20);
        arrowIcon.setFitWidth(BUTTON_SIZE * .25);
        ImageView arrowIcon2 = new ImageView(new Image("/insert.png"));
        arrowIcon2.setFitHeight(BUTTON_SIZE * .20);
        arrowIcon2.setFitWidth(BUTTON_SIZE * .25);
        //sets up the down arrow buttons
        downArrow2 = createIconButton(arrowIcon2, SCALE/2);
        downArrow1 = createIconButton(arrowIcon, SCALE/2);

        //sets up the insert document button
        insertDoc = new Button("Insert Document");
        insertDoc.setPrefSize(300 * SCALE, 25 * SCALE);
        insertDoc.getStyleClass().add("squircle-button");
        insertDoc.setFont(new Font(16));
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

    private TextField createField() {
        TextField theField = new TextField();
        theField.setMinSize(100 * SCALE, 25 * SCALE);
        theField.getStyleClass().add("custom-text-entry");
        theField.setFont(new Font(16));
        return theField;
    }

    private Text createText(String text) {
        Text theText = new Text(text);
        theText.getStyleClass().add("white-text");
        theText.setFont(new Font(16));
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

}
