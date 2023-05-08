package com.gestorusuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

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
            alAbrirLeerDatos(stage);
        });

        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            alCerrarGuardarDatos(stage);
        });
    }
    public static void main(String[] args) {
        launch();
    }

    public void alAbrirLeerDatos(Stage stage){
        try {
            String url = "jdbc:mariadb://localhost:3306/Passwd", user = "root", passwd = "daniel";
            Connection con = DriverManager.getConnection(url,user,passwd);
            Statement s = con.createStatement();


            ResultSet usuarios = s.executeQuery("select * from Usuario");
            while (usuarios.next()){
                ControllerLogin.loginData.add(new User(usuarios.getString("usuario"),usuarios.getString("password"),
                        usuarios.getString("permisos")));
            }
            con.close();
            stage.show();
        }catch (SQLException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void alCerrarGuardarDatos(Stage stage) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            String url = "jdbc:mariadb://localhost:3306/Passwd";
            String user = "root";
            String passwd = "daniel";
            con = DriverManager.getConnection(url, user, passwd);
            String query = "insert into Usuario (usuario, password, permisos) values (?, ?, ?)";
            ps = con.prepareStatement(query);
            for (int i = 0; i < ControllerLogin.loginData.size(); i++) {
                String insertUser = ControllerLogin.loginData.get(i).getUser();
                String insertPass = ControllerLogin.loginData.get(i).getPassword();
                String insertPermisos = ControllerLogin.loginData.get(i).getPermisos();
                ps.setString(1, insertUser);
                ps.setString(2, insertPass);
                ps.setString(3, insertPermisos);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar Connection: " + e.getMessage());
                }
            }
            stage.close();
        }
    }
}