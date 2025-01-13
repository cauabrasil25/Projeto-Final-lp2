package br.ufrn.imd.model;

/**
 * Esta classe representa um usuario no jogo. 
 * Contem o nome de usuario, senha e a pontuacao do usuario.
 */
public class User {

    /**
     * Nome de usuario do usuario no jogo.
     */
    private String username;

    /**
     * Senha do usuario para login.
     */
    private String password;

    /**
     * Nivel de dificuldade selecionado pelo usuario.
     */
    private String difficulty;

    /**
     * Pontuacao maxima do usuario no jogo.
     */
    private int maxScore;

    /**
     * Construtor padrao da classe User, inicializando os valores de usuario, senha, dificuldade e pontuacao.
     */
    public User() {
        this.username = "";
        this.password = "";
        this.difficulty = "Normal";
        this.maxScore = 0;
    }

    /**
     * Retorna a pontuacao maxima do usuario.
     * 
     * @return A pontuacao maxima do usuario.
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Define a pontuacao maxima do usuario.
     * 
     * @param maxScore A pontuacao maxima a ser definida para o usuario.
     */
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * Retorna a senha do usuario.
     * 
     * @return A senha do usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuario.
     * 
     * @param password A senha a ser definida para o usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Retorna o nivel de dificuldade do usuario.
     * 
     * @return O nivel de dificuldade do usuario.
     */
    public String getDifficulty() {
        return difficulty;
    }
    
    /**
     * Define o nivel de dificuldade do usuario.
     * 
     * @param difficulty O nivel de dificuldade a ser definido para o usuario.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Retorna o nome de usuario do usuario.
     * 
     * @return O nome de usuario do usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o nome de usuario do usuario.
     * 
     * @param username O nome de usuario a ser definido.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
