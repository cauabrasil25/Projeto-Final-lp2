package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Controlador para a tela de boas-vindas.
 * Esta classe gerencia os eventos de botao na tela inicial.
 */
public class WelcomeController {

    /**
     * Logger utilizado para registrar eventos na classe WelcomeController.
     */
    @SuppressWarnings("exports")
    public static final Logger LOGGER = Logger.getLogger(WelcomeController.class.getName());

    /**
     * Metodo chamado quando o botao de login e clicado.
     * Realiza a troca para a tela de login.
     */
    @FXML
    private void onLoginButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Login.fxml", "Login");
    }

    /**
     * Metodo chamado quando o botao de registro e clicado.
     * Realiza a troca para a tela de registro.
     */
    @FXML
    private void onRegisterButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Register.fxml", "Register");
    }

    /**
     * Metodo chamado quando o botao de sair e clicado.
     * Exibe uma confirmacao para o usuario e fecha o aplicativo caso confirmada.
     */
    @FXML
    private void onExitButtonClick() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            MenuApplication.saveUsers();
            System.exit(0);
        }
    }

}
