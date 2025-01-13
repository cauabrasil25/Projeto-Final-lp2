package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.view.MenuApplication;
import br.ufrn.imd.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador para a tela de pontuacoes (ScoreBoard).
 * Este controlador gerencia a exibição da tabela de pontuacoes dos usuarios.
 */
public class ScoreBoardController {

    private UsersList usersList;

    @FXML
    private TableView<User> scoreTable;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, Integer> scoreColumn;

    /**
     * Inicializa os componentes da tela de pontuacoes.
     * Carrega a lista de usuarios e exibe as 20 melhores pontuacoes.
     */
    @FXML
    private void initialize() {
        usersList = MenuApplication.getUsersList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("maxScore"));

        // Ordena os usuarios pela pontuacao maxima
        usersList.userOrdering();
        int size = Math.min(usersList.getUsers().size(), 20);
        ObservableList<User> topUsers = FXCollections.observableArrayList(usersList.getUsers().subList(0, size));

        // Define os itens da tabela
        scoreTable.setItems(topUsers);
    }

    /**
     * Define a lista de usuarios a ser exibida.
     * 
     * @param usersList A lista de usuarios a ser exibida.
     */
    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    /**
     * Lida com a acao do botao de voltar.
     * Retorna para o menu principal.
     */
    @FXML
    private void handleBackButtonAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");
    }
}
