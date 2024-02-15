module com.example.jfx_lrs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jfx_lrs to javafx.fxml;
    exports com.example.jfx_lrs;
}