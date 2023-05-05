package com.gestorusuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Aplicación");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnShown(windowEvent -> {
            windowEvent.consume();
            try {
                alAbrirLeerDatos(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                alCerrarGuardarDatos(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void alAbrirLeerDatos(Stage stage) throws IOException {
        File f = new File("C:\\Users\\Daniel\\IdeaProjects\\GestorUsuarios\\src\\main\\Data\\database.txt");
        Scanner leeDatos = new Scanner(f);
        StringTokenizer st;
        if (leeDatos.hasNextLine()){
            while (leeDatos.hasNextLine()){
                st = new StringTokenizer(leeDatos.nextLine(),",");
                ControllerLogin.loginData.add(new User(st.nextToken(),st.nextToken(),st.nextToken()));
            }
            leeDatos.close();
            stage.show();
        }
    }
    public void alCerrarGuardarDatos(Stage stage) throws IOException {
        File f = new File("C:\\Users\\Daniel\\IdeaProjects\\GestorUsuarios\\src\\main\\Data\\database.txt");
        FileWriter guardaDatos = new FileWriter(f);
        for (int i = 0; i< ControllerLogin.loginData.size(); i++){
            guardaDatos.write(ControllerLogin.loginData.get(i).getUser() + "," + ControllerLogin.loginData.get(i).getPassword() + "," + ControllerLogin.loginData.get(i).getPermisos() + "\n");
        }
        guardaDatos.close();
        stage.close();
    }
    public static void main(String[] args) {
        launch();
    }
}