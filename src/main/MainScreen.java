/*
 *
 */
package main;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * GUI for the main screen where it is considered
 * the "home" of the application.
 *
 * @author Paul Lee,
 * @version Spring 2022
 */
public class MainScreen {

    /** The name of our team name */
    private static final String teamName = "MacroSoft";

    /** Version number of the application */
    private final Version myVersion;

    /** The user of the application */
    private final User myUser;

    /** Layout used for the application */
    private final BorderPane borderPane;

    /** Layout used to store the buttons in a horizontal row */
    private final HBox hBox;

    /** Layout used to store the buttons in a vertical column */
    private final VBox vBox;

    /** Button used to display the About screen */
    private final Button aboutButton;

    /** Button to save the informations */
    private final Button saveButton;

    /** Button to load any saved information */
    private final Button loadButton;

    /** Button to delete an item */
    private final Button deleteButton;

    /** Button to create a folder */
    private final Button createFolder;

    /** Button to redirect the user back to the home page */
    private final Button homeButton;

    /**
     * Constructor that initializes the instance field.
     * Currently, the owner of the application is initialized
     * in this method.
     */
    public MainScreen() {
        myUser = new User("ExampleBob", "ExampleBob@gmail.com");

        myVersion = new Version();

        borderPane = new BorderPane();
        hBox = new HBox();
        vBox = new VBox();
        aboutButton = new Button("About");
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        deleteButton = new Button("Delete");
        createFolder = new Button("Create Folder");
        homeButton = new Button("Home");

    }
    /**
     * Creates the main page of the GUI application.
     * Calls the version box method to display the
     * About screen when the appropriate button is pressed.
     *
     * @return the main page GUI.
     */
    public BorderPane homePage() {

        aboutButton.setPrefSize(75,75);
        saveButton.setPrefSize(75,75);
        loadButton.setPrefSize(75,75);

        hBox.setSpacing(5);
        versionBox();
        hBox.getChildren().addAll(aboutButton,saveButton,loadButton);

        deleteButton.setPrefSize(75,75);
        createFolder.setPrefSize(75,75);
        homeButton.setPrefSize(75,75);
        vBox.getChildren().addAll(deleteButton,createFolder,homeButton);

        Text text1 = new Text("Welcome...");

        // Places the layouts in the border pane
        borderPane.setTop(hBox);

        borderPane.setRight(vBox);
        borderPane.setCenter(text1);

        return borderPane;
    }

    /**
     * Creates the About screen, which displays who the application
     * is registered to, then lists the authors of the application.
     * The current version of the application is shown at the bottom.
     */
    private void versionBox() {
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

        aboutButton.setOnAction(e -> dialog.showAndWait());
    }
}
