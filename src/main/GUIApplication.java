package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class GUIApplication extends Application {
    private final Version myVersion = new Version();
    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);
        versionBox(gridPane);

        Text text1 = new Text("Test");
        gridPane.add(text1, 1, 1);

        Scene scene = new Scene(gridPane);
        stage.setTitle("IterationOne");
        stage.setScene(scene);
        stage.show();
    }

    public void versionBox(GridPane gridPane) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Version");
        dialog.setContentText("Version " + myVersion.getVersionNumber());

        ButtonType exit = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(exit);

        Button button = new Button("About");
        button.setOnAction(e -> dialog.showAndWait());

        gridPane.add(button, 2, 0);
    }
    public static void main(String[] args) {

        launch(args);
    }
}
