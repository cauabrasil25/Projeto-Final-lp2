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

public class GameController {


    @FXML
    private AnchorPane buttonContainer;

    @FXML
    private Text scoreLabel;
    
    @FXML
    private Text timeLabel;
    
    //Final do jogo
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
    private int initialTime = 90; // Tempo inicial de 90 segundos
    private int timeRemaining;

    @FXML
    public void initialize() {
    	buttonMainMenu.setVisible(false);
        buttonTryAgain.setVisible(false);
        labelFinalScore.setVisible(false);
        textFinalScore.setVisible(false);
        textCongratulations.setVisible(false);
        
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

        // Configurar a contagem regressiva de 1min30s (90 segundos)
        Timeline gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();

    }

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

        // O botão desaparece após 1 segundo
        Timeline disappearance = new Timeline(new KeyFrame(Duration.seconds(secsToSpawn), ev -> {
            buttonContainer.getChildren().remove(button);
        }));
        disappearance.setCycleCount(1);
        disappearance.play();
    }

    private void updateScore() {
        scoreLabel.setText("Scoreboard: " + clicks*clickValue);
    }
    
    private void updateTime() {
        if (gameRunning && timeRemaining > 0) {
            timeRemaining--;
            timeLabel.setText("Remaining Time: " + timeRemaining + "s");
        } else {
            endGame();
        }
    }

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
    
    @FXML
    private void handleTryAgainAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Game.fxml", "Game");
    }
    
    @FXML
    private void handleMainMenuAction() {
    	ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");
    }
}
