package br.ufrn.imd.view;

import br.ufrn.imd.model.User;
import javafx.application.Application;
import javafx.stage.Stage;
import br.ufrn.imd.controller.ScreenManager;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.DataLoad;
import br.ufrn.imd.model.DataRead;
import br.ufrn.imd.model.DataSave;

import java.io.IOException;

public class MenuApplication extends Application {

    private static UsersList usersList = new UsersList();
    private static User activeUser = new User();
    private static String difficulty;

    @SuppressWarnings("exports")
	@Override
    public void start(Stage stage) throws IOException {
        DataRead dataRead = new DataRead();
        dataRead.readJsonFile();
        DataLoad dataLoad = new DataLoad();
        usersList = dataLoad.loadJsonFile();
        
        ScreenManager.setPrimaryStage(stage);
        stage.setTitle("Welcome");
        ScreenManager.switchScreen("/br/ufrn/imd/view/Welcome.fxml", "Welcome");
    }

    @Override
    public void stop() {
        saveUsers();
    }

    public static void main(String[] args) {
        launch();
    }

    public static UsersList getUsersList() {
        return usersList;
    }

    public static void setActiveUser(User user) {
        activeUser.setMaxScore(user.getMaxScore());
        activeUser.setUsername(user.getUsername());
        activeUser.setPassword(user.getPassword());
        activeUser.setPassword(user.getDifficulty());
    }

    public static String getUsername() {
    	return activeUser.getUsername();
    }
    
    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        MenuApplication.difficulty = difficulty;
        activeUser.setDifficulty(difficulty);
        usersList.updateUser(activeUser);
    }
    
    public static void saveUsers() {
    	DataSave dataSave = new DataSave();
        dataSave.saveJsonFile(usersList);
    }
    
    public static void updateScore(int score) {
    	if(score > activeUser.getMaxScore()) {
    		activeUser.setMaxScore(score);
    		usersList.updateUser(activeUser);
    	}
    }

}
