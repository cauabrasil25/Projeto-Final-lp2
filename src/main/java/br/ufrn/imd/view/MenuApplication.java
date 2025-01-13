package br.ufrn.imd.view;

import br.ufrn.imd.model.User;
import javafx.application.Application;
import javafx.stage.Stage;
import br.ufrn.imd.controller.ScreenManager;
import br.ufrn.imd.dao.UsersList;
import br.ufrn.imd.model.DataLoad;
import br.ufrn.imd.model.DataRead;
import br.ufrn.imd.model.DataSave;

import java.io.IOException;

/**
 * A classe MenuApplication representa a aplicacao JavaFX principal que gerencia a tela inicial
 * e a navegacao entre as telas, alem de manipular os dados do usuario, como pontuacoes e preferencias.
 * Esta classe e responsavel por inicializar os dados dos usuarios e permitir a interacao com a interface grafica.
 */
public class MenuApplication extends Application {

    /**
     * Lista de usuarios carregada a partir de um arquivo JSON.
     */
    private static UsersList usersList = new UsersList();

    /**
     * Usuario atualmente ativo na aplicacao.
     */
    private static User activeUser = new User();

    /**
     * Nivel de dificuldade do usuario.
     */
    private static String difficulty;

    /**
     * Metodo que inicializa a aplicacao, le os dados dos usuarios e configura a interface grafica.
     * 
     * @param stage O estagio principal da aplicacao, onde a interface grafica sera exibida.
     * @throws IOException Se ocorrer um erro ao carregar os arquivos JSON.
     */
    @Override
    public void start(Stage stage) throws IOException {
        DataRead dataRead = new DataRead();
        dataRead.readJsonFile(); // Le o arquivo JSON contendo os dados
        DataLoad dataLoad = new DataLoad();
        usersList = dataLoad.loadJsonFile(); // Carrega os dados de usuarios a partir de um arquivo

        ScreenManager.setPrimaryStage(stage);
        stage.setTitle("Welcome");
        ScreenManager.switchScreen("/br/ufrn/imd/view/Welcome.fxml", "Welcome");
    }

    /**
     * Metodo chamado quando a aplicacao e fechada. Salva os dados dos usuarios antes do termino.
     */
    @Override
    public void stop() {
        saveUsers();
    }

    /**
     * Metodo principal que inicia a aplicacao.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Obtem a lista de usuarios.
     * 
     * @return A lista de usuarios carregada.
     */
    public static UsersList getUsersList() {
        return usersList;
    }

    /**
     * Define o usuario ativo da aplicacao com os dados fornecidos.
     * 
     * @param user O usuario a ser definido como ativo.
     */
    public static void setActiveUser(User user) {
        activeUser.setMaxScore(user.getMaxScore());
        activeUser.setUsername(user.getUsername());
        activeUser.setPassword(user.getPassword());
        activeUser.setDifficulty(user.getDifficulty());
    }

    /**
     * Obtem o nome de usuario do usuario ativo.
     * 
     * @return O nome de usuario do usuario ativo.
     */
    public static String getUsername() {
        return activeUser.getUsername();
    }

    /**
     * Obtem o nivel de dificuldade do usuario ativo.
     * 
     * @return O nivel de dificuldade do usuario ativo.
     */
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * Define o nivel de dificuldade do usuario ativo.
     * 
     * @param difficulty O nivel de dificuldade a ser definido.
     */
    public static void setDifficulty(String difficulty) {
        MenuApplication.difficulty = difficulty;
        activeUser.setDifficulty(difficulty);
        usersList.updateUser(activeUser);
    }

    /**
     * Salva a lista de usuarios em um arquivo JSON.
     */
    public static void saveUsers() {
        DataSave dataSave = new DataSave();
        dataSave.saveJsonFile(usersList); // Salva os dados dos usuarios no arquivo JSON
    }

    /**
     * Atualiza a pontuacao maxima do usuario ativo, caso a nova pontuacao seja maior.
     * 
     * @param score A nova pontuacao a ser verificada e atualizada.
     */
    public static void updateScore(int score) {
        if(score > activeUser.getMaxScore()) {
            activeUser.setMaxScore(score);
            usersList.updateUser(activeUser);
        }
    }
}
