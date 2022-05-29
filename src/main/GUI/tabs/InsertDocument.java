package main.GUI.tabs;

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

import static javafx.application.Application.launch;

public class InsertDocument extends Tab {

    private static final int BUTTON_SIZE = 100;

    private final BorderPane borderPane;
    private final GridPane gridPane;
    private final Text FilePath;
    private final Text Obj;
    private final Text Folder;
    private final Text Name;
    private final TextField FilePathBox;
    private final TextField ObjectBox;
    private final TextField FolderBox;
    private final TextField NameBox;
    private final Button Select;

    private final Button DownArrow1;
    private final Button DownArrow2;
    private final Button InsertDoc;
    public InsertDocument(String buttonName, Image icon) {
        super(buttonName, icon);
        borderPane = new BorderPane();
        gridPane = new GridPane();
        FilePath = new Text("FilePath");
        FilePath.getStyleClass().add("white-text");
        Obj = new Text("Object");
        Obj.getStyleClass().add("white-text");
        Folder = new Text("Folder");
        Folder.getStyleClass().add("white-text");
        Name = new Text("Name");
        Name.getStyleClass().add("white-text");
        FilePathBox = new TextField();
        FilePathBox.setMinSize(100, 25);
        ObjectBox = new TextField();
        ObjectBox.setMinSize(100, 25);
        FolderBox = new TextField();
        FolderBox.setMinSize(100, 25);
        NameBox = new TextField();
        NameBox.setMinSize(100, 25);
        Select = new Button("Select");
        DownArrow1 = new Button();
        DownArrow1.getStyleClass().add("transparent-square-button");
        DownArrow2 = new Button();
        DownArrow2.getStyleClass().add("transparent-square-button");
        ImageView Arrowimage = new ImageView(new Image("/insert.png"));
        ImageView Arrowimage2 = new ImageView(new Image("/insert.png"));
        Arrowimage.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage.setFitWidth(BUTTON_SIZE * .40);
        Arrowimage2.setFitHeight(BUTTON_SIZE * .20);
        Arrowimage2.setFitWidth(BUTTON_SIZE * .40);
        DownArrow1.setGraphic(Arrowimage);
        DownArrow2.setGraphic(Arrowimage2);
        InsertDoc = new Button("Insert Document");
        InsertDoc.setPrefSize(300, 25);
    }

    @Override
    public Pane buildView(Stage stage) {

        gridPane.setMinSize(500,  250);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(FilePath, 0, 0);
        gridPane.add(Obj, 0, 1);
        gridPane.add(Folder, 0, 2);
        gridPane.add(Name, 0, 3);
        gridPane.add(FilePathBox, 1, 0);
        gridPane.add(ObjectBox, 1, 1);
        gridPane.add(FolderBox,1, 2);
        gridPane.add(NameBox, 1, 3);
        gridPane.add(DownArrow1, 2, 1);
        gridPane.add(DownArrow2, 2, 2);
        gridPane.add(InsertDoc, 1, 4);
        gridPane.add(Select, 2, 0);
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
