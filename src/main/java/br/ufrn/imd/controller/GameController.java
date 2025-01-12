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
    
    private int clicks = 0;
    private boolean gameRunning = true;
    private int clickValue;
    private double secsToSpawn;
    private int timeRemaining = 20; // Tempo inicial de 90 segundos

    @FXML
    public void initialize() {
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

        // Configurar a contagem regressiva de 1min30s (90 segundos)
        Timeline gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();

        // Criar botões aleatórios
        Timeline buttonTimeline = new Timeline(new KeyFrame(Duration.seconds(secsToSpawn), e -> spawnRandomButton()));
        buttonTimeline.setCycleCount(Timeline.INDEFINITE);
        buttonTimeline.play();
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
        timeLabel.setText("Time's Up!");
        scoreLabel.setText("Game Over! Final Score: " + clicks*clickValue);
        MenuApplication.updateScore(clicks*clickValue);
    }
}
