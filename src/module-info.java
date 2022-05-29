module MacroSoft.TCSS360 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens main.GUI to javafx.fxml;
    exports archive;
    opens archive to javafx.fxml;
    exports main.data;
    opens main.data to javafx.fxml;
    exports main.GUI;
}