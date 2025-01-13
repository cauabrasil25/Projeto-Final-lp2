package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UserException;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.User;
import br.ufrn.imd.view.MenuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador para a tela de cadastro de usuario.
 * Este controlador gerencia o processo de registro de novos usuarios.
 */
public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UsersList usersList = MenuApplication.getUsersList();

    /**
     * Define a lista de usuarios a ser utilizada.
     * 
     * @param usersList A lista de usuarios a ser utilizada.
     */
    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    /**
     * Lida com a acao do botao de registro.
     * Registra um novo usuario se o nome de usuario nao existir.
     * Exibe um alerta se o usuario ja estiver cadastrado.
     */
    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            // Verifica se o usuario ja existe
            usersList.findUserByUsername(username);

            // Se o usuario ja existir, exibe um alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("User already registered. Please login.");
            alert.showAndWait();
            
            // Redireciona para a tela de login
            ScreenManager.switchScreen("/br/ufrn/imd/view/Login.fxml", "Login");
        } catch (UserException e) {
            // Se o usuario nao existir, cria o novo usuario
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // Adiciona o novo usuario a lista
            usersList.addUser(user);

            // Exibe alerta de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("User registered successfully. Please login.");
            alert.showAndWait();
            
            // Redireciona para a tela de login
            ScreenManager.switchScreen("/br/ufrn/imd/view/Login.fxml", "Login");
        }
        System.out.println("Username: " + username + ", Password: " + password);
    }

    /**
     * Lida com a acao do botao de voltar.
     * Retorna para a tela de boas-vindas.
     */
    @FXML
    private void handleBackButtonAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Welcome.fxml", "Welcome");
    }
}
