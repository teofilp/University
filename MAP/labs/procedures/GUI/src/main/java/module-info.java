module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.company;


    opens com.example.gui to javafx.fxml;
    exports com.example.gui;
}