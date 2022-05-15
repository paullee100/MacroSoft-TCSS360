/*
 *
 */
package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller of the GUI to call other class's method that would
 * transition between the application.
 *
 * @author Paul Lee,
 * @version Spring 2022
 */
public class GUIController extends Application {

    /** The width of the GUI */
    public static final int WIDTH = 500;

    /** The height of the GUI */
    public static final int HEIGHT = 500;

    /** LoginScreen class to be used to call one of the methods */
    private LoginScreen loginScreen;

    /**
     * Starts up with the log in screen and calls the accountInput method
     * in the LoginScreen class to check the credentials before
     * moving to the main screen.
     * @param stage to set the scene and to display the GUI to the user.
     */
    @Override
    public void start(Stage stage) {
        loginScreen = new LoginScreen();

        stage.setTitle("IterationOne");
        Scene loginPage = new Scene(loginScreen.loginGUI(), WIDTH, HEIGHT);

        stage.setScene(loginPage);
        stage.show();

        loginScreen.accountInput(stage);

    }

    /**
     * Main method to launch the GUI.
     *
     * @param args argument
     */
    public static void main(String[] args) {

        launch(args);
    }
}
