/*
 *
 */
package main.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.data.User;

/**
 * A class to make the log in GUI and checks the
 * user inputted credentials before allowing them
 * into the program.
 *
 * @author Paul Lee, Gabriel Bryan
 * @version Spring 2022
 */
public class LoginScreen {

    /** Dimensions of stage */
    private final double WIDTH = 500;
    private final double HEIGHT = 500;

    /** Layout for the application as a whole */
    private final BorderPane borderPane;

    /** Layout used for the application window */
    private final GridPane gridPane;

    /** Text to indicate to the user to input username in the text field */
    private final Text username;
    /** Text to indicate to the user to input password in the text field */
    private final Text password;
    /** Textfield for the user to input their username */
    private final TextField inputUser;
    /** Textfield for the user to input their password */
    private final TextField inputPass;

    /** Button to submit both textfield to login */
    private final Button submitButton;
    /** Button to quit the program */
    private final Button quitButton;

    /** Checks if the user inputted the correct information */
    private Boolean loginComplete = false;

    /** The GUI Controller that the app returns too after finishing the login procedure */
    private GUIController guiControl;

    private Stage myStage;
    /**
     * Constructor that initializes the instance field.
     */
    public LoginScreen(GUIController theController, Stage stage) {
        //Initializes the field variables passed as parameters
        guiControl = theController;
        myStage = stage;

        //Initializes the panes for the application
        gridPane = new GridPane();
        borderPane = new BorderPane();

        //Initializes the text prompts
        username = new Text("Username: ");
        username.getStyleClass().add("white-text");
        password = new Text("Email: ");
        password.getStyleClass().add("white-text");

        //Initializes the text field boxes
        inputUser = new TextField();
        inputUser.getStyleClass().add("custom-text-entry");
        inputPass = new TextField();
        inputPass.getStyleClass().add("custom-text-entry");

        //Initialize buttons
        submitButton = new Button("Submit");
        submitButton.getStyleClass().add("squircle-button");
        quitButton = new Button("Quit");
        quitButton.getStyleClass().add("squircle-button");
    }

    /**
     * Creates the log in screen of the GUI.
     *
     * @return the log in GUI.
     */
    public Pane loginGUI() {
        myStage.setHeight(HEIGHT);
        myStage.setWidth(WIDTH);

        //Sets the size, and spacing of the login page.
        gridPane.setMinSize(500,  500);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.setAlignment(Pos.CENTER);

        //Adds all elements to the grid pane.
        gridPane.add(username, 0, 0);
        gridPane.add(inputUser, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(inputPass, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(quitButton, 1, 2);

        //Adds the elements to the border pane.
        borderPane.setTop(guiControl.createToolBar());
        borderPane.setCenter(gridPane);

        //Generates the submit button as well as login verification.
        accountInput();

        return borderPane;
    }

    /**
     * Checks the entries typed in the textfield of the username
     * and password to see if they are correct. If correct, the application
     * will send them to the main page of the application.
     *
     * @return true if user inputted correct credentials, false otherwise.
     */
    public boolean accountInput() {

        // Everytime the button is pressed, it checks the
        // text string in the text field.
        submitButton.setOnAction(actionEvent -> {
            String checkUser = inputUser.getText();
            String checkEmail = inputPass.getText();
            System.out.println(checkUser + " " + checkEmail);
            User.activeUser = new User(checkUser, checkEmail);
            guiControl.buildMainView();
            //stage.setScene(mainPage);
            loginComplete = true;

            /* Username and password checker and if correct
            changes the scene to the main page. (case-sensitive).
            return boolean is not necessary, but could be useful.
            If it is not useful, we can easily change it to void and delete the loginComplete.
            if (checkUser.equals("MacroSoft") && checkEmail.equals("tcss360")) {
                stage.setScene(mainPage);
                loginComplete = true;
            } else {
                System.out.println("Incorrect Password"); // Testing to see if user inputs incorrect info
            }*/

        });

        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        return loginComplete;
    }
}
