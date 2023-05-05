package com.gestorusuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSesion implements Initializable{
    @FXML
    private Label usuario;
    @FXML
    private Label permisos;
    @FXML
    private ListView<String> verUsuarios;
    @FXML
    protected void alEntrarConseguirDatos(User sesion){
        usuario.setText("Usuario: " + sesion.getUser());
        permisos.setText(permisos.getText().concat(" " + sesion.getPermisos()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i=0;i<ControllerLogin.loginData.size();i++){
            verUsuarios.getItems().add(ControllerLogin.loginData.get(i).getUser());
        }
    }
    @FXML
    protected void alPulsarCambiarUsuario(ActionEvent e){
        //PROGRAMAR ESTE MÉTODO
    }
    @FXML
    protected void alPulsarResetearContraseña(ActionEvent e){
        //PROGRAMAR ESTE MÉTODO
    }
    @FXML
    protected void alPulsarEliminarUsuario(ActionEvent e){
        //PROGRAMAR ESTE MÉTODO
    }
}