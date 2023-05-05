module com.gestorusuarios {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gestorusuarios to javafx.fxml;
    exports com.gestorusuarios;
}