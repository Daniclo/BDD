package com.gestorusuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerSesionUser {

    @FXML
    private Label usuario;

    @FXML
    private Label permisos;
    @FXML
    private Button logout;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    protected void alPulsarLogout(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void alEntrarConseguirDatos(User sesion){
        usuario.setText("Usuario: " + sesion.getUser());
        permisos.setText("Permisos: " + sesion.getPermisos());
    }
}