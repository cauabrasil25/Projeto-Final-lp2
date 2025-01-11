package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.logging.Logger;

public class WelcomeController {

    @SuppressWarnings("exports")
	public static final Logger LOGGER = Logger.getLogger(WelcomeController.class.getName());

    @FXML
    private void onLoginButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Login.fxml", "Login");
    }

    @FXML
    private void onRegisterButtonClick() {
    	ScreenManager.switchScreen("/br/ufrn/imd/view/Register.fxml", "Register");
    }

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
