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

public class ControllerSesionAdmin implements Initializable{
    @FXML
    private Label usuario;
    @FXML
    private Label permisos;
    @FXML
    private ListView<String> verUsuarios;
    protected String infoEdicion;
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
        verUsuarios.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);
    }
    String selectedUser;
    private void selectionChanged(ObservableValue<? extends String> Observable, String olVal, String newVal){
        ObservableList<String> selectedItems = verUsuarios.getSelectionModel().getSelectedItems();
        selectedUser = (selectedItems.isEmpty())?"No Selected Item":selectedItems.toString();
    }
    @FXML
    protected void alPulsarCambiarUsuario(ActionEvent e) throws IOException {
        for (int i=0;i<ControllerLogin.loginData.size();i++){
            if (selectedUser.equals("["+ControllerLogin.loginData.get(i).getUser()+"]")){
                FXMLLoader loader = new FXMLLoader(App.class.getResource("Edicion.fxml"));
                Parent root = loader.load();
                ControllerEdicion controllerEdicion = loader.getController();
                controllerEdicion.alAbrirCambiarTextoUsuario("User");
                Scene scene = new Scene(root,350,350);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                Boolean respuesta = controllerEdicion.getRespuesta();
                if (respuesta){
                    String user = controllerEdicion.getUser();
                    ControllerLogin.loginData.get(i).setUser(user);
                    verUsuarios.getItems().clear();
                    for (int j=0;j<ControllerLogin.loginData.size();j++){
                        verUsuarios.getItems().add(ControllerLogin.loginData.get(j).getUser());
                    }
                }
                break;
            }
        }
    }
    @FXML
    protected void alPulsarResetearPassword(ActionEvent e) throws IOException {
        for (int i=0;i<ControllerLogin.loginData.size();i++){
            if (selectedUser.equals("["+ControllerLogin.loginData.get(i).getUser()+"]")){
                FXMLLoader loader = new FXMLLoader(App.class.getResource("Edicion.fxml"));
                Parent root = loader.load();
                ControllerEdicion controllerEdicion = loader.getController();
                controllerEdicion.alAbrirCambiarTextoContraseña("Pass");
                Scene scene = new Scene(root,350,350);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                Boolean respuesta = controllerEdicion.getRespuesta();
                if (respuesta){
                    String pass = controllerEdicion.getPass();
                    ControllerLogin.loginData.get(i).setPassword(pass);
                }
                break;
            }
        }
    }
    @FXML
    protected void alPulsarEliminarUsuario(ActionEvent e) throws IOException {
        for (int i=0;i<ControllerLogin.loginData.size();i++){
            if (selectedUser.equals("["+ControllerLogin.loginData.get(i).getUser()+"]")){
                FXMLLoader loader = new FXMLLoader(App.class.getResource("Edicion.fxml"));
                Parent root = loader.load();
                ControllerEdicion controllerEdicion = loader.getController();
                controllerEdicion.alAbrirCambiarTextoBorrar("Delete");
                Scene scene = new Scene(root,350,350);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                Boolean respuesta = controllerEdicion.getRespuesta();
                if (respuesta){
                    ControllerLogin.loginData.remove(i);
                    verUsuarios.getItems().clear();
                    for (int j=0;j<ControllerLogin.loginData.size();j++){
                        verUsuarios.getItems().add(ControllerLogin.loginData.get(j).getUser());
                    }
                }
                break;
            }
        }
    }
}