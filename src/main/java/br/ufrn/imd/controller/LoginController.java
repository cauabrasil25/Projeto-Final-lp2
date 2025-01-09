package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UserException;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.DataLoad;
import br.ufrn.imd.model.DataRead;
import br.ufrn.imd.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private User authenticated(String username, String password) throws UserException {
        DataRead dataRead = new DataRead();
        dataRead.readJsonFile();
        DataLoad dataLoad = new DataLoad();
        UsersList usersList = dataLoad.loadJsonFile();
        return usersList.findUser(username, password);
    }

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            User user = authenticated(username, password);
            System.out.println("User authenticated: " + user.getUsername());
        } catch (UserException e) {
            System.out.println("User not authenticated: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            if (e.getMessage().equals("User not found")) {
                alert.setContentText("User not found, please register");
            } else if (e.getMessage().equals("Incorrect password")) {
                alert.setContentText("Incorrect password, please try again");
            }
            alert.showAndWait();
        }
        System.out.println("Username: " + username + ", Password: " + password);
    }

}
