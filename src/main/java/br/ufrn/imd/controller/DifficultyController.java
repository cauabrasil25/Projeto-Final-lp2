package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Controlador para a selecao de dificuldade no jogo.
 * Este controlador gerencia as acoes dos botões para selecionar os niveis de dificuldade.
 */
public class DifficultyController {

    @FXML
    private Button easyButton;

    @FXML
    private Button normalButton;

    @FXML
    private Button hardButton;

    /**
     * Lida com o evento de clique no botão "Easy".
     * Exibe uma confirmação sobre a dificuldade escolhida.
     */
    @FXML
    private void onEasyButtonClick() {
        showConfirmation("Easy", "The score will be lower in this difficulty level.", easyButton);
    }

    /**
     * Lida com o evento de clique no botão "Normal".
     * Exibe uma confirmação sobre a dificuldade escolhida.
     */
    @FXML
    private void onNormalButtonClick() {
        showConfirmation("Normal", "This is the default difficulty level, with normal score.", normalButton);
    }

    /**
     * Lida com o evento de clique no botão "Hard".
     * Exibe uma confirmação sobre a dificuldade escolhida.
     */
    @FXML
    private void onHardButtonClick() {
        showConfirmation("Hard", "Targets appear and disappear faster, and the score is doubled.", hardButton);
    }

    /**
     * Exibe uma janela de confirmação quando uma dificuldade é selecionada.
     * Caso a dificuldade seja confirmada, a tela é alterada para o menu principal.
     *
     * @param difficulty A dificuldade selecionada.
     * @param message A mensagem associada a dificuldade.
     * @param button O botão que acionou a confirmacao.
     */
    private void showConfirmation(String difficulty, String message, Button button) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Difficulty Confirmation");
        alert.setHeaderText("You have selected: " + difficulty);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Difficulty confirmed: " + difficulty);

            MenuApplication.setDifficulty(difficulty);

            ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");
        }
    }
}
