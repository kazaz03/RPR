module com.example.lv7z1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lv7z1 to javafx.fxml;
    exports com.example.lv7z1;
}