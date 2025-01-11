package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UserException;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.User;
import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UsersList usersList;

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    private User authenticated(String username, String password) throws UserException {
        return usersList.findUser(username, password);
    }

    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            User user = authenticated(username, password);
            System.out.println("User already registered" + user.getUsername());
        } catch (UserException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            if (e.getMessage().equals("User not found")) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                usersList.addUser(user);
                alert.setContentText("User registered, please login");
            } else if (e.getMessage().equals("Incorrect password")) {
                alert.setContentText("User already registered");
            }
            alert.showAndWait();
        }
        System.out.println("Username: " + username + ", Password: " + password);
    }
}
