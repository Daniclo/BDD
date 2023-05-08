package com.gestorusuarios;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEdicion {
    @FXML
    private Label textoEdicion;
    @FXML
    private TextField textoCambios;
    @FXML
    private Button confirmar;
    private String infoEdicion;
    @FXML
    private Label textoError;

    @FXML
    protected void alAbrirCambiarTextoUsuario(String infoEdicion){
        textoEdicion.setText("Introduce el nuevo usuario:");
        this.infoEdicion = infoEdicion;
    }
    @FXML
    protected void alAbrirCambiarTextoContraseña(String infoEdicion){
        textoEdicion.setText("Introduce la nueva contraseña:");
        this.infoEdicion = infoEdicion;
    }
    @FXML
    protected void alAbrirCambiarTextoBorrar(String infoEdicion){
        textoEdicion.setText("Introduce la contraseña de \nadministrador para ejecutar \nesta acción");
        this.infoEdicion = infoEdicion;
    }
    private String user;
    private String pass;
    boolean respuesta;
    @FXML
    protected void confirmar() throws IOException { //Falta impedir que puedas borrar tu propio usuario.
        if (infoEdicion.equals("User")){
            user = textoCambios.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Se ha modificado el usuario correctamente");
            alert.showAndWait();
            respuesta = true;
            Stage stage = (Stage) this.confirmar.getScene().getWindow();
            stage.close();
        }else  if (infoEdicion.equals("Pass")){
            pass = textoCambios.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Se ha modificado la contraseña correctamente");
            alert.showAndWait();
            respuesta = true;
            Stage stage = (Stage) this.confirmar.getScene().getWindow();
            stage.close();
        }else if (infoEdicion.equals("Delete")){
            for (int i=0;i<ControllerLogin.loginData.size();i++){
                if (ControllerLogin.loginData.get(i).getPermisos().equals("admin")){
                    if (textoCambios.getText().equals(ControllerLogin.loginData.get(i).getPassword())){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Información");
                        alert.setContentText("Se ha eliminado el usuario correctamente");
                        alert.showAndWait();
                        respuesta = true;
                        Stage stage = (Stage) this.confirmar.getScene().getWindow();
                        stage.close();
                        break;
                    }else {
                        textoError.setText("Error contraseña incorrecta");
                    }
                }else {
                    textoError.setText("Error contraseña incorrecta");
                }
            }
        }
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
    public String getPass(){return pass;}
    public Boolean getRespuesta(){
        return respuesta;
    }
}
