/*
 *
 */
package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class to make the log in GUI and checks the
 * user inputted credentials before allowing them
 * into the program.
 *
 * @author Paul Lee,
 * @version Spring 2022
 */
public class LoginScreen {

    /** Layout used for the application */
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

    /** Uses string to check if the username is correct */
    private String checkUser;

    /** Uses string to check if the password is correct */
    private String checkPass;

    /** Checks if the user inputted the correct information */
    private Boolean loginComplete = false;

    /**
     * Constructor that initializes the instance field.
     */
    public LoginScreen() {
        gridPane = new GridPane();

        username = new Text("Username/Email: ");
        password = new Text("Password: ");

        inputUser = new TextField();
        inputPass = new TextField();

        submitButton = new Button("Submit");
        quitButton = new Button("Quit");
    }

    /**
     * Creates the log in screen of the GUI.
     *
     * @return the log in GUI.
     */
    public GridPane loginGUI() {
        gridPane.setMinSize(200, 200);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(username, 0, 0);
        gridPane.add(inputUser, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(inputPass, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(quitButton, 1, 2);

        return gridPane;
    }

    /**
     * Checks the entries typed in the textfield of the username
     * and password to see if they are correct. If correct, the application
     * will send them to the main page of the application.
     *
     * @return true if user inputted correct credentials, false otherwise.
     */
    public boolean accountInput(Stage stage) {
        MainScreen mainScreen = new MainScreen();
        Scene mainPage = new Scene(mainScreen.homePage(), GUIController.WIDTH, GUIController.HEIGHT);

        // Everytime the button is pressed, it checks the
        // text string in the text field.
        submitButton.setOnAction(actionEvent -> {
            checkUser = inputUser.getText();
            checkPass = inputPass.getText();

            // Username and password checker and if correct
            // changes the scene to the main page. (case-sensitive).
            // return boolean is not necessary, but could be useful.
            // If it is not useful, we can easily change it to void and delete the loginComplete.
            if (checkUser.equals("MacroSoft") && checkPass.equals("tcss360")) {
                stage.setScene(mainPage);
                loginComplete = true;
            } else {
                System.out.println("Incorrect Password"); // Testing to see if user inputs incorrect info
            }

        });

        return loginComplete;
    }
}
