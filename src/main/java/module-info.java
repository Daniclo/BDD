module com.gestorusuarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.gestorusuarios to javafx.fxml;
    exports com.gestorusuarios;
}