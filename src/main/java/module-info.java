module com.example.jfx_lrs {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;
   // requires org.junit.jupiter.params;


    opens com.example.jfx_lrs to javafx.fxml;
    exports com.example.jfx_lrs;
}