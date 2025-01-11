package br.ufrn.imd.controller;

import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.view.MenuApplication;
import br.ufrn.imd.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScoreBoardController {

    private UsersList usersList;

    @FXML
    private TableView<User> scoreTable;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, Integer> scoreColumn;

    @FXML
    private void initialize() {
        usersList = MenuApplication.getUsersList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("maxScore"));

        usersList.userOrdering();
        int size = Math.min(usersList.getUsers().size(), 20);
        ObservableList<User> topUsers = FXCollections.observableArrayList(usersList.getUsers().subList(0, size));

        scoreTable.setItems(topUsers);
    }

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }
    
    @FXML
    private void handleBackButtonAction() {
        ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");
    }
}
