package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class MainMenuController {
	
	@FXML
	private TextField difficultyTextField;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	public void initialize() {
	    difficultyTextField.setText(MenuApplication.getDifficulty());
	    usernameTextField.setText(MenuApplication.getUsername());
	}


    @FXML
    private void onPlayButtonClick() {

    }

    @FXML
    private void onDifficultyButtonClick() {
    	ScreenManager.switchScreen("/br/ufrn/imd/view/Difficulty.fxml", "Difficulty");
    }

    @FXML
    private void onScoreboardButtonClick() {
    	ScreenManager.switchScreen("/br/ufrn/imd/view/ScoreBoard.fxml", "ScoreBoard");
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
