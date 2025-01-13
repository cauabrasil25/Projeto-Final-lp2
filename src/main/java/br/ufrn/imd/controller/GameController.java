package br.ufrn.imd.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;

import br.ufrn.imd.view.MenuApplication;

/**
 * Controlador para o jogo.
 * Este controlador gerencia a logica do jogo, incluindo a pontuacao e o tempo.
 */
public class GameController {

    @FXML
    private AnchorPane buttonContainer;

    @FXML
    private Text scoreLabel;
    
    @FXML
    private Text timeLabel;
    
    @FXML
    private Button buttonMainMenu;
    @FXML
    private Button buttonTryAgain;
    @FXML
    private Text labelFinalScore;
    @FXML
    private Text textFinalScore;
    @FXML
    private Text textCongratulations;
    
    private int clicks = 0;
    private boolean gameRunning = true;
    private int clickValue;
    private double secsToSpawn;
    private int initialTime = 60;
    private int timeRemaining;

    /**
     * Inicializa o jogo com a dificuldade selecionada e configura o tempo e a pontuacao.
     */
    @FXML
    public void initialize() {
    	buttonMainMenu.setVisible(false);
        buttonTryAgain.setVisible(false);
        labelFinalScore.setVisible(false);
        textFinalScore.setVisible(false);
        textCongratulations.setVisible(false);
        
    	// Define o valor do clique e o tempo entre o aparecimento dos botões conforme a dificuldade
    	switch(MenuApplication.getDifficulty()) {
    	case "Easy":
    		clickValue = 1;
    		secsToSpawn = 2;
    		break;
    	case "Hard":
    		clickValue = 4;
    		secsToSpawn = 0.5;
    		break;
    	default:
    		clickValue = 2;
    		secsToSpawn = 1;
    		break;
    	}
        startGame();
    }

    /**
     * Inicia o jogo, incluindo o cronometro e o spawn dos botões.
     */
    private void startGame() {
        // Inicializar o placar
    	clicks = 0;
        updateScore();
        
        // Inicializar o tempo
        timeRemaining = initialTime;
        updateTime();
        
        // Criar botões aleatórios
        Timeline buttonTimeline = new Timeline(new KeyFrame(Duration.seconds(secsToSpawn), e -> spawnRandomButton()));
        buttonTimeline.setCycleCount(Timeline.INDEFINITE);
        buttonTimeline.play();

        // Configurar a contagem regressiva de 1min
        Timeline gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();

    }

    /**
     * Cria um botão aleatório na tela.
     * O botão aparece em uma posição aleatória e, ao ser clicado, aumenta a pontuacao.
     */
    private void spawnRandomButton() {
        if (!gameRunning) return;

        Random random = new Random();
        Button button = new Button("Click Me!");
        button.setLayoutX(random.nextInt((int) buttonContainer.getWidth() - 100)); // Espaço aleatório dentro da largura
        button.setLayoutY(random.nextInt((int) buttonContainer.getHeight() - 40)); // Espaço aleatório dentro da altura

        // Ao clicar no botão, a pontuação aumenta
        button.setOnAction(event -> {
        	clicks++;
            updateScore();
            buttonContainer.getChildren().remove(button); // Remove o botão após o clique
        });

        // Adiciona o botão ao contêiner
        buttonContainer.getChildren().add(button);

        // Definir tempo para o botão desaparecer
        if(secsToSpawn+1 >= timeRemaining) {        	
        	buttonContainer.getChildren().remove(button);
        } else {        	
        	Timeline disappearance = new Timeline(new KeyFrame(Duration.seconds(secsToSpawn), ev -> {
        		buttonContainer.getChildren().remove(button);
        	}));
        	disappearance.setCycleCount(1);
        	disappearance.play();
        }
    }

    /**
     * Atualiza o placar exibido na tela.
     */
    private void updateScore() {
        scoreLabel.setText("Scoreboard: " + clicks*clickValue);
    }
    
    /**
     * Atualiza o tempo restante no jogo.
     * Quando o tempo acaba, o jogo é finalizado.
     */
    private void updateTime() {
        if (gameRunning && timeRemaining > 0) {
            timeRemaining--;
            timeLabel.setText("Remaining Time: " + timeRemaining + "s");
        } else {
            endGame();
        }
    }

    /**
     * Finaliza o jogo, exibindo a pontuacao final e botões para tentar novamente ou voltar ao menu principal.
     */
    private void endGame() {
        gameRunning = false;
        timeLabel.setVisible(false);
        scoreLabel.setVisible(false);
        
        buttonMainMenu.setVisible(true);
        buttonTryAgain.setVisible(true);
        labelFinalScore.setVisible(true);
        textFinalScore.setVisible(true);
        textCongratulations.setVisible(true);
        
        labelFinalScore.setText(""+ clicks*clickValue);
        MenuApplication.updateScore(clicks*clickValue);
    }
    
    /**
     * Lida com a acao do botao 'Try Again'.
     * Reinicia o jogo.
     */
    @FXML
    private void handleTryAgainAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Game.fxml", "Game");
    }
    
    /**
     * Lida com a acao do botao 'Main Menu'.
     * Volta para o menu principal.
     */
    @FXML
    private void handleMainMenuAction() {
    	ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");
    }
}
