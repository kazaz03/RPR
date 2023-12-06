module com.example.lv6z1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lv6z1 to javafx.fxml;
    exports com.example.lv6z1;
}