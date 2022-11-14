package com.example.lemoncalc;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.io.IOException;
import java.time.LocalDateTime;

public class LemonCalculator extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LemonCalculator.class.getResource("mainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Lemon Calculator");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
        System.out.println(now + " Приложение запущено");

        stage.setOnCloseRequest(event -> logOut(stage));
    }
    public void logOut(Stage stage) {
        stage.close();
        System.out.println(now + " Приложение закрыто");
    }
    public static void main(String[] args) {
        launch();
    }
    LocalDateTime now = LocalDateTime.now();
}