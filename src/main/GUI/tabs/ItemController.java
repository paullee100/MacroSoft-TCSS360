package main.GUI.tabs;

import main.GUI.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ItemController extends Tab {

    public ItemController(String buttonName, Image icon) {
        super(buttonName, icon);
    }

    @Override
    public Pane buildView(Stage stage) {
        return null;
    }

}
