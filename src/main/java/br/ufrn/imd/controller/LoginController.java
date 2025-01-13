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
 * Controlador para a tela de login.
 * Este controlador gerencia as interacoes de login do usuario na aplicacao.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UsersList usersList = MenuApplication.getUsersList();
    private boolean authenticated = false;

    /**
     * Verifica se o usuario esta autenticado.
     * 
     * @return true se o usuario estiver autenticado, false caso contrario.
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Define a lista de usuarios.
     * 
     * @param usersList a lista de usuarios.
     */
    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    /**
     * Autentica o usuario com o nome de usuario e senha fornecidos.
     * 
     * @param username o nome de usuario.
     * @param password a senha do usuario.
     * @return o usuario autenticado.
     * @throws UserException se o usuario nao for encontrado ou a senha for incorreta.
     */
    private User authenticate(String username, String password) throws UserException {
        return usersList.findUser(username, password);
    }

    /**
     * Lida com a acao do botao 'Login'.
     * Este metodo autentica o usuario e redireciona para o menu principal.
     */
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

            // Troca de tela para o MainMenu usando o ScreenManager
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

    /**
     * Lida com a acao do botao 'Back'.
     * Este metodo redireciona para a tela de boas-vindas.
     */
    @FXML
    private void handleBackButtonAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/Welcome.fxml", "Welcome");
    }
}
