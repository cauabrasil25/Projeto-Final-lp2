package br.ufrn.imd.view;

import br.ufrn.imd.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.DataLoad;
import br.ufrn.imd.model.DataRead;
import br.ufrn.imd.model.DataSave;

import java.io.IOException;

public class MenuApplication extends Application {

    private static UsersList usersList = new UsersList();
    private static User activeUser = new User();
    private static String difficulty;

    @Override
    public void start(Stage stage) throws IOException {
        DataRead dataRead = new DataRead();
        dataRead.readJsonFile();
        DataLoad dataLoad = new DataLoad();
        usersList = dataLoad.loadJsonFile();

        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
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
    }

    public static String getUsername() {
    	return activeUser.getUsername();
    }
    
    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        MenuApplication.difficulty = difficulty;
    }
    
    public static void saveUsers() {
    	DataSave dataSave = new DataSave();
        dataSave.saveJsonFile(usersList);
    }

}
