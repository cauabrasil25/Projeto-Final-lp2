package br.ufrn.imd.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;

public class ScreenManager {
    private static Stage primaryStage;

    @SuppressWarnings("exports")
	public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchScreen(String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(sceneTitle);
            primaryStage.show();
        } catch (IOException e) {
        	WelcomeController.LOGGER.log(Level.SEVERE, "Error loading Login.fxml", e);
        }
    }
}
