package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeController {

    private static final Logger LOGGER = Logger.getLogger(WelcomeController.class.getName());

    @FXML
    private void onLoginButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/Login.fxml"));
            Parent root = fxmlLoader.load();

            LoginController loginController = fxmlLoader.getController();
            loginController.setUsersList(MenuApplication.getUsersList());

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (loginController.isAuthenticated()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("User logged in successfully");
                alert.showAndWait();

                Stage welcomeStage = (Stage) root.getScene().getWindow();
                welcomeStage.close();

                FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/MainMenu.fxml"));
                Parent mainMenuRoot = mainMenuLoader.load();
                Stage mainMenuStage = new Stage();
                mainMenuStage.setTitle("Main Menu");
                mainMenuStage.setScene(new Scene(mainMenuRoot));
                mainMenuStage.show();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading Login.fxml", e);
        }
    }

    @FXML
    private void onRegisterButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/Register.fxml"));
            Parent root = fxmlLoader.load();

            RegisterController registerController = fxmlLoader.getController();
            registerController.setUsersList(MenuApplication.getUsersList());

            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading Login.fxml", e);
        }
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
