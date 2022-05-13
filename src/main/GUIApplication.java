package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIApplication extends Application {
    private final Version myVersion = new Version();
    private static final String teamName = "MacroSoft";
    private User myUser;
    @Override
    public void start(Stage stage) {
        myUser = new User("ExampleBob", "ExampleBob@gmail.com");

        Scene scene = new Scene(loginScreen(stage), 500, 500);
        stage.setTitle("IterationOne");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private BorderPane mainScreen() {
        BorderPane borderPane = new BorderPane();

        HBox horizontalBox = new HBox();
        Button button = new Button("About");
        button.setPrefSize(75,75);
        Button saveButton = new Button("Save");
        saveButton.setPrefSize(75,75);
        Button loadButton = new Button("Load");
        loadButton.setPrefSize(75,75);

        horizontalBox.setSpacing(5);
        versionBox(horizontalBox, button);
        horizontalBox.getChildren().addAll(button,saveButton,loadButton);

        VBox verticalBox = new VBox();
        Button deleteButton = new Button("Delete");
        deleteButton.setPrefSize(75,75);
        Button createFolder = new Button("Create Folder");
        createFolder.setPrefSize(75,75);
        Button homeButton = new Button("Home");
        homeButton.setPrefSize(75,75);
        verticalBox.getChildren().addAll(deleteButton,createFolder,homeButton);

        Pane pane = new Pane();
        Text text1 = new Text("Welcome...");
        pane.getChildren().add(text1);

        // Places the layouts in the border pane
        borderPane.setTop(horizontalBox);
        borderPane.setRight(verticalBox);
        borderPane.setCenter(text1);

        return borderPane;
    }
    private GridPane loginScreen(Stage stage) {
        GridPane gridPane = new GridPane();
        Text username = new Text("Username/Email: ");
        TextField inputUser = new TextField();

        Text password = new Text("Password");
        TextField inputPass = new TextField();

        Button submit = new Button("Submit");
        Button quit = new Button("Quit");

        gridPane.setMinSize(200, 200);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(username, 0, 0);
        gridPane.add(inputUser, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(inputPass, 1, 1);
        gridPane.add(submit, 0, 2);
        gridPane.add(quit, 1, 2);

        accountInput(inputUser, inputPass, submit, stage);

        return gridPane;
    }

    private void accountInput(TextField inputUser, TextField inputPass, Button submit, Stage stage) {
       // if (inputUser.getCharacters().equals("5") && inputPass.getCharacters().equals("5")) {
            Scene mainScene = new Scene(mainScreen(), 500, 500);
            submit.setOnAction(e -> stage.setScene(mainScene));
            stage.show();
      //  }
    }

    private void versionBox(HBox hBox, Button button) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("About");
        StringBuilder content = new StringBuilder();

        content.append("This app is registered to:\n" + myUser.toString());
        content.append("\nThis app is provided by " + teamName);
        content.append("\nAuthors: ");
        content.append("\nGabe Bryan - gabeb544@uw.edu");
        content.append("\nAnteh Hsu - gan86650@uw.edu");
        content.append("\nWei Wei Chien - weiwei88@uw.edu");
        content.append("\nAlex Larsen - alexlars@uw.edu");
        content.append("\nPaul Lee - plee83@uw.edu");
        content.append("\nVersion " + myVersion.getVersionNumber());

        dialog.setContentText(content.toString());

        ButtonType exit = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(exit);

        button.setOnAction(e -> dialog.showAndWait());

//        hBox.getChildren().add(button);
    }
    public static void main(String[] args) {

        launch(args);
    }
}
