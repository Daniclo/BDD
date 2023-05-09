package com.gestorusuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerLogin {
    protected static ArrayList<User> loginData = new ArrayList<>();
    @FXML
    private Label msg;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    protected void alPulsarRegistra() {
        if (userField.getText().equals("") || passField.getText().equals("")){
            msg.setStyle("-fx-text-fill: red");
            msg.setText("Error. Rellena ambos campos para proceder.");
        }else {
            if (loginData.isEmpty()){
                loginData.add(new User(userField.getText(),passField.getText(),"admin"));
                msg.setStyle("-fx-text-fill: black");
                msg.setText("Registro exitoso");
                System.out.println("Usuario añadido");
            }else {
                loginData.add(new User(userField.getText(),passField.getText(),"user"));
                msg.setStyle("-fx-text-fill: black");
                msg.setText("Registro exitoso");
                System.out.println("Usuario añadido");
            }
        }
    }
    @FXML
    protected void alPulsarLogin(ActionEvent e) throws IOException {
        if (userField.getText().equals("") || passField.getText().equals("")){
            msg.setStyle("-fx-text-fill: red");
            msg.setText("Error. Rellena ambos campos para proceder.");
        }else {
            for (int i=0;i<loginData.size();i++){
                if (loginData.get(i).getUser().equals(userField.getText())){
                    if (loginData.get(i).getPassword().equals(passField.getText())){
                        if (loginData.get(i).getPermisos().equals("admin")){
                            User sesion = new User(loginData.get(i).getUser(),loginData.get(i).getPassword(),loginData.get(i).getPermisos());
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("SesionAdmin.fxml"));
                            root = loader.load();
                            ControllerSesionAdmin controllerSesionAdmin = loader.getController();
                            controllerSesionAdmin.alEntrarConseguirDatos(sesion);
                            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        } else {
                            User sesion = new User(loginData.get(i).getUser(),loginData.get(i).getPassword(),loginData.get(i).getPermisos());
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("SesionUser.fxml"));
                            root = loader.load();
                            ControllerSesionUser controllerSesionUser = loader.getController();
                            controllerSesionUser.alEntrarConseguirDatos(sesion);
                            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        }
                    }else {
                        msg.setStyle("-fx-text-fill: red");
                        msg.setText("Error. Contraseña incorrecta");
                    }
                }else {
                    msg.setStyle("-fx-text-fill: red");
                    msg.setText("Error. Usuario incorrecto");
                }
            }
        }
    }
}