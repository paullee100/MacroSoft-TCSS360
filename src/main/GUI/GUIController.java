/*
 *
 */
package main.GUI;

import main.GUI.tabs.InsertDocument;
import main.GUI.tabs.ItemController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.GUI.tabs.Search;

/**
 * Controller of the GUI to call other class's method that would
 * transition between the application.
 *
 * @author Paul Lee, Gabriel Bryan
 * @version Spring 2022
 */
public class GUIController extends Application {

    /** The width of the GUI */
    public static final int WIDTH = 500;

    /** The height of the GUI */
    public static final int HEIGHT = 500;

    /** LoginScreen class to be used to call one of the methods */
    private LoginScreen loginScreen;

    /** These are all of the different tabs that represent different features */
    private Tab[] tabs;

    /** This is the stage for the application that will be used throughout its execution */
    private static Stage myStage;

    /**
     * Starts up with the log in screen and calls the accountInput method
     * in the LoginScreen class to check the credentials before
     * moving to the main screen.
     * @param stage to set the scene and to display the GUI to the user.
     */
    @Override
    public void start(Stage stage) {
        //Create tabs
        createTabs();

        myStage = stage;

        loginScreen = new LoginScreen(this);

        stage.setTitle("IterationTwo");
        Scene loginPage = new Scene(loginScreen.loginGUI(), WIDTH, HEIGHT);
        loginPage.getStylesheets().add("/StyleSheet.css");

        stage.setScene(loginPage);
        stage.show();

        loginScreen.accountInput(stage);

    }

    public void buildMainView() {
        VBox tabs = tabChanger();

        BorderPane mainPane = new BorderPane();

        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainScene.getStylesheets().add("/StyleSheet.css");
        myStage.setScene(mainScene);

        //Add the elements
        mainPane.setLeft(tabs);
        myStage.setMaximized(true);
    }


    public VBox tabChanger() {
        VBox tabBar = new VBox();
        tabBar.setSpacing(10);

        //Adds all the tab buttons to the vertical box
        for(Tab aTab: tabs) {
            tabBar.getChildren().add(aTab.getTabButton());
        }

        Background background = new Background(new BackgroundFill(Color.rgb(40, 43, 56), CornerRadii.EMPTY, Insets.EMPTY));
        tabBar.setBackground(background);
        //tabBar.getChildren().addAll(home, search, insertDoc);
        return tabBar;
    }

    /**
     * Main method to launch the GUI.
     *
     * @param args argument
     */
    public static void main(String[] args) {

        launch(args);
    }

    private void createTabs() {
        tabs = new Tab[3];
        tabs[0] = new ItemController("Home", new Image("/homeIcon.png"));
        tabs[1] = new Search("Search", new Image("/searchIcon.png"));
        tabs[2] = new InsertDocument("Insert Document", new Image("/documentIcon.png"));
    }
}
