module com.example.lab1011 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab1011 to javafx.fxml;
    exports com.example.lab1011;
}