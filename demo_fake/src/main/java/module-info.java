module com.example.demo_fake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.demo_fake to javafx.fxml;
    exports com.example.demo_fake;
}