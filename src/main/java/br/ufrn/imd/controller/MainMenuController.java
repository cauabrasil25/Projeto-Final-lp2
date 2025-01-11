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

public class MainMenuController {

    @FXML
    private void onPlayButtonClick() {

    }

    @FXML
    private void onDifficultyButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/Difficulty.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Difficulty");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, "Error loading Difficulty.fxml", e);
        }
    }

    @FXML
    private void onScoreboardButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/ScoreBoard.fxml"));
            Parent root = fxmlLoader.load();

            ScoreBoardController scoreBoardController = fxmlLoader.getController();
            scoreBoardController.setUsersList(MenuApplication.getUsersList());

            Stage stage = new Stage();
            stage.setTitle("Scoreboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, "Error loading ScoreBoard.fxml", e);
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
            System.exit(0);
        }
    }

}
