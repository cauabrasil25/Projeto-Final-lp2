package br.ufrn.imd.controller;

import br.ufrn.imd.model.AboutData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AboutController {
	
	@FXML
	private TableView<AboutData> aboutTable;
	
	@FXML
	private TableColumn<AboutData, String> difficultyColumn;
	
	@FXML
	private TableColumn<AboutData, String> valueColumn;
	
	@FXML
	private TableColumn<AboutData, String> timeColumn;
	
	@FXML
    public void initialize() {
		// Configurar como as colunas acessam os atributos do objeto
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Adicionar valores fixos na tabela
        ObservableList<AboutData> data = FXCollections.observableArrayList(
            new AboutData("Easy", "1", "2s"),
            new AboutData("Normal", "2", "1s"),
            new AboutData("Hard", "4", "0.5s")
        );

        aboutTable.setItems(data);
    }
    
    @FXML
    private void onBackButtonClick() {
    	ScreenManager.switchScreen("/br/ufrn/imd/view/MainMenu.fxml", "Main Menu");
    }

}
