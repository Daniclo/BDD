package com.gestorusuarios;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEdicion {
    @FXML
    private Label textoEdicion;
    @FXML
    private TextField textoCambios;
    @FXML
    private Button confirmar;

    @FXML
    protected void alAbrirCambiarTextoUsuario(){
        textoEdicion.setText("Introduce el nuevo usuario:");
    }
    @FXML
    protected void alAbrirCambiarTextoContraseña(){
        textoEdicion.setText("Introduce la nueva contraseña:");
    }
    String user;
    boolean respuesta;
    @FXML
    protected void confirmar(){
        user = textoCambios.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText("Se ha añadido correctamente");
        alert.showAndWait();
        respuesta = true;
        Stage stage = (Stage) this.confirmar.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void cancelar(){
        respuesta = false;
        Stage stage = (Stage) this.confirmar.getScene().getWindow();
        stage.close();
    }
    public String getUser(){
        return user;
    }
    public Boolean getRespuesta(){
        return respuesta;
    }
}
