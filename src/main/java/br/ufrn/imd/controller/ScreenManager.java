package br.ufrn.imd.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Gerenciador de telas da aplicacao.
 * Esta classe e responsavel por controlar a troca de telas e a configuracao do palco principal.
 */
public class ScreenManager {
    /**
     * Palco principal da aplicacao.
     */
    private static Stage primaryStage;

    /**
     * Define o palco principal da aplicacao.
     * 
     * @param stage O palco principal a ser definido.
     */
    @SuppressWarnings("exports")
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);
    }

    /**
     * Realiza a troca de tela na aplicacao.
     * 
     * @param fxmlFile O caminho do arquivo FXML da tela a ser carregada.
     * @param sceneTitle O titulo da cena a ser exibida.
     */
    public static void switchScreen(String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            // Criar um BorderPane e centralizar o conteudo na posicao CENTER
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
