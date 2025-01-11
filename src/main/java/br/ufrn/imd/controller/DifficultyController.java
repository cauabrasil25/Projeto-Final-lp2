package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class DifficultyController {

    @FXML
    private Button easyButton;

    @FXML
    private Button normalButton;

    @FXML
    private Button hardButton;

    @FXML
    private void onEasyButtonClick() {
        showConfirmation("Easy", "The score will be lower in this difficulty level.", easyButton);

    }

    @FXML
    private void onNormalButtonClick() {
        showConfirmation("Normal", "This is the default difficulty level, with normal score.", normalButton);
    }

    @FXML
    private void onHardButtonClick() {
        showConfirmation("Hard", "Targets appear and disappear faster, and the score is doubled.", hardButton);
    }

    private void showConfirmation(String difficulty, String message, Button button) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Difficulty Confirmation");
        alert.setHeaderText("You have selected: " + difficulty);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Difficulty confirmed: " + difficulty);

            MenuApplication.setDifficulty(difficulty);

            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }
    }
}
