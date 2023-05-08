package com.gestorusuarios;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerSesionUser {

    @FXML
    private Label usuario;

    @FXML
    private Label permisos;

    @FXML
    protected void alEntrarConseguirDatos(User sesion){
        usuario.setText("Usuario: " + sesion.getUser());
        permisos.setText("Permisos: " + sesion.getPermisos());
    }
}
