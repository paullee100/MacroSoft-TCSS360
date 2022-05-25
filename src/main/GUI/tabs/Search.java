package main.GUI.tabs;

import main.GUI.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Search extends Tab {

    public Search(String buttonName, Image icon) {
        super(buttonName, icon);
    }

    @Override
    public Pane buildView(Stage stage) {
        return null;
    }
}
