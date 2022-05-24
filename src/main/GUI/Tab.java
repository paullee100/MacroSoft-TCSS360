package main.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Tab {
    private Button tabButton;

    private static final int BUTTON_SIZE = 100;

    public Tab (String buttonName, Image icon) {
        tabButton = new Button();
        tabButton.getStyleClass().add("transparent-square-button");
        tabButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        tabButton.setTooltip(new Tooltip(buttonName));

        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(BUTTON_SIZE);
        iconView.setFitWidth(BUTTON_SIZE);
        tabButton.setGraphic(iconView);
    }

    public abstract Pane buildView(Stage stage);

    public Button getTabButton() {
        return tabButton;
    }
}
