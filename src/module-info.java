module MacroSoft.TCSS360 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.json;
    requires org.apache.commons.io;
    requires java.desktop;

    opens main.GUI to javafx.fxml;
    exports archive;
    opens archive to javafx.fxml;
    //exports main;
    exports main.data;
    opens main.data to javafx.fxml;
    exports main.GUI;
    exports main.GUI.tabs;
    opens main.GUI.tabs to javafx.fxml;
    //opens main.GUI to javafx.fxml;
}