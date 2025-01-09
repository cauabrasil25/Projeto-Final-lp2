package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UserException;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.User;
import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

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
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            User user = authenticated(username, password);
            System.out.println("User authenticated: " + user.getUsername());
            for (User u : usersList.getUsers()) {
                if (u.getUsername().equals(username)) {
                    user.setMaxScore(u.getMaxScore());
                    break;
                }
            }
            MenuApplication.setActiveUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/MainMenu.fxml"));
            Parent root = fxmlLoader.load();
            Stage mainMenuStage = new Stage();
            mainMenuStage.setTitle("Main Menu");
            mainMenuStage.setScene(new Scene(root));
            mainMenuStage.show();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Username: " + username + ", Password: " + password);
    }

}
