module com.example.lemoncalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lemoncalc to javafx.fxml;
    exports com.example.lemoncalc;
}