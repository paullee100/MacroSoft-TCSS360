module MacroSoft.TCSS360 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens main to javafx.fxml;
    exports main;
    exports archive;
    opens archive to javafx.fxml;
}