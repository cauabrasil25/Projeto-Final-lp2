package br.ufrn.imd.controller;

import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

/**
 * Controlador para o menu principal do jogo.
 * Este controlador gerencia as interacoes no menu principal da aplicacao.
 */
public class MainMenuController {

    @FXML
    private TextField difficultyTextField;

    @FXML
    private TextField usernameTextField;

    /**
     * Metodo de inicializacao.
     * Este metodo preenche os campos de texto com as informacoes de dificuldade e nome de usuario.
     */
    @FXML
    public void initialize() {
        difficultyTextField.setText(MenuApplication.getDifficulty());
        usernameTextField.setText(MenuApplication.getUsername());
    }

    /**
     * Lida com a acao do botao 'Play'.
     * Este metodo redireciona para a tela de jogo.
     */
    @FXML
    private void onPlayButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Game.fxml", "Game");
    }

    /**
     * Lida com a acao do botao 'Difficulty'.
     * Este metodo redireciona para a tela de selecao de dificuldade.
     */
    @FXML
    private void onDifficultyButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Difficulty.fxml", "Difficulty");
    }

    /**
     * Lida com a acao do botao 'Scoreboard'.
     * Este metodo redireciona para a tela de placar.
     */
    @FXML
    private void onScoreboardButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/ScoreBoard.fxml", "ScoreBoard");
    }

    /**
     * Lida com a acao do botao 'About'.
     * Este metodo redireciona para a tela sobre o jogo.
     */
    @FXML
    private void onAboutButtonClick() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/About.fxml", "About");
    }

    /**
     * Lida com a acao do botao 'Exit'.
     * Este metodo exibe uma confirmacao antes de fechar a aplicacao.
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
