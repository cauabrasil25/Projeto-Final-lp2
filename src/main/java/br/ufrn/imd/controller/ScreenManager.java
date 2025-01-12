package br.ufrn.imd.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;

public class ScreenManager {
    private static Stage primaryStage;

    @SuppressWarnings("exports")
	public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);
    }

    public static void switchScreen(String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            // Criar um BorderPane e centralizar o conteúdo na posição CENTER
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(root);

            // Criar a cena e configurar o tamanho
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle(sceneTitle);
            primaryStage.show();
        } catch (IOException e) {
        	WelcomeController.LOGGER.log(Level.SEVERE, "Error loading Login.fxml", e);
        }
    }
}
