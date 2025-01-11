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

    private UsersList usersList = MenuApplication.getUsersList();

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            // Verifica se o usuário já existe
            usersList.findUser(username, password);

            // Se o usuário já existir, ele lançará uma exceção
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("User already registered. Please login.");
            alert.showAndWait();
            
            ScreenManager.switchScreen("/br/ufrn/imd/view/Login.fxml", "Login");
        } catch (UserException e) {
            // Se o usuário não existir, cria o novo usuário
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // Adiciona o novo usuário à lista
            usersList.addUser(user);

            // Alerta de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("User registered successfully. Please login.");
            alert.showAndWait();
            
            ScreenManager.switchScreen("/br/ufrn/imd/view/Login.fxml", "Login");
        }
        System.out.println("Username: " + username + ", Password: " + password);
    }
    
    @FXML
    private void handleBackButtonAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Welcome.fxml", "Welcome");
    }
}
