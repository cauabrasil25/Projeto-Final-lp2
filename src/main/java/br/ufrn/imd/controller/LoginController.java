package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UserException;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.User;
import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UsersList usersList = MenuApplication.getUsersList();
    private boolean authenticated = false;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    private User authenticate(String username, String password) throws UserException {
        return usersList.findUser(username, password);
    }

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        try {
            User user = authenticate(username, password);
            user.setMaxScore(usersList.getUsers().stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .map(User::getMaxScore)
                    .orElse(0));

            MenuApplication.setActiveUser(user);
            MenuApplication.setDifficulty(user.getDifficulty());
            this.authenticated = true;

            // ✅ Troca de tela para o MainMenu usando o ScreenManager
            ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");

        } catch (UserException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage().equals("User not found") ? 
                "User not found, please register." : 
                "Incorrect password, please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBackButtonAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Welcome.fxml", "Welcome");
    }
}
