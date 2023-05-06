package com.gestorusuarios;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
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
    String selectedUser;
    private void selectionChanged(ObservableValue<? extends String> Observable, String olVal, String newVal){
        ObservableList<String> selectedItems = verUsuarios.getSelectionModel().getSelectedItems();
        selectedUser = (selectedItems.isEmpty())?"No Selected Item":selectedItems.toString();
    }
    @FXML
    protected void alPulsarCambiarUsuario(ActionEvent e) throws IOException { //ERROR GRANDE AQUÍ, ESTO ES NULO Y NO SE POR QUÉ COJONES
        verUsuarios.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);
        for (int i=0;i<ControllerLogin.loginData.size();i++){
            if (selectedUser.equals(ControllerLogin.loginData.get(i).getUser())){
                FXMLLoader loader = new FXMLLoader(App.class.getResource("Edicion.fxml"));
                Parent root = loader.load();
                ControllerEdicion controllerEdicion = loader.getController();
                controllerEdicion.alAbrirCambiarTextoUsuario();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                Boolean respuesta = controllerEdicion.getRespuesta();
                if (respuesta){
                    String user = controllerEdicion.getUser();
                    ControllerLogin.loginData.get(i).setUser(user);
                    for (int j=0;j<ControllerLogin.loginData.size();j++){
                        verUsuarios.getItems().add(ControllerLogin.loginData.get(j).getUser());
                    }
                }
                break;
            }
        }
    }
    @FXML
    protected void alPulsarResetearPassword(ActionEvent e){
        //PROGRAMAR ESTE MÉTODO
    }
    @FXML
    protected void alPulsarEliminarUsuario(ActionEvent e){
        //PROGRAMAR ESTE MÉTODO
    }
}