package archive;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.data.User;
import main.data.Settings;

public class GUIApplication extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;

    private final Settings myVersion = new Settings();
    private static final String teamName = "MacroSoft";
    private User myUser;
    @Override
    public void start(Stage stage) {
        myUser = new User("ExampleBob", "ExampleBob@gmail.com");

        Scene scene = new Scene(loginScreen(stage), WIDTH, HEIGHT);
        stage.setTitle("IterationOne");
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane mainScreen(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Background background = new Background(new BackgroundFill(Color.rgb(51, 45, 64), CornerRadii.EMPTY, Insets.EMPTY));
        borderPane.setBackground(background);

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

        /*VBox verticalBox = new VBox();
        Button deleteButton = new Button("Delete");
        deleteButton.setPrefSize(75,75);
        Button createFolder = new Button("Create Folder");
        createFolder.setPrefSize(75,75);
        Button homeButton = new Button("Home");
        homeButton.setPrefSize(75,75);
        verticalBox.getChildren().addAll(deleteButton,createFolder,homeButton);*/

        Pane pane = new Pane();
        Text text1 = new Text("Welcome...");
        text1.setFill(Color.WHITE);
        pane.getChildren().add(text1);

        // Places the layouts in the border pane
        borderPane.setBottom(horizontalBox);
        //borderPane.setRight(verticalBox);
        borderPane.setCenter(text1);
        borderPane.setLeft(mainTabBar(stage));

        return borderPane;
    }

    /**
     * This tab bar is for navigating between the different screens in the application.
     * For example there is a home button that takes you to the home screen.
     * @author Gabriel Bryan
     * @param stage
     * @return the virtual box element of the main tab bar
     */
    private VBox mainTabBar(Stage stage) {
        VBox tabBar = new VBox();

        Button home = new Button("Home");
        home.setPrefSize(75, 75);
        Button search = new Button("Search");
        search.setPrefSize(75, 75);
        Button insertDoc = new Button("Insert Document");
        insertDoc.setPrefSize(75, 75);

        Background background = new Background(new BackgroundFill(Color.rgb(55, 50, 77), CornerRadii.EMPTY, Insets.EMPTY));
        tabBar.setBackground(background);
        tabBar.getChildren().addAll(home, search, insertDoc);
        return tabBar;
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
            Scene mainScene = new Scene(mainScreen(stage), WIDTH, HEIGHT);
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
