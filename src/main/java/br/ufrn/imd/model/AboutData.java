package br.ufrn.imd.model;

/**
 * Esta classe representa os dados sobre a dificuldade, valor e tempo.
 */
public class AboutData {
    private String difficulty;
    private String value;
    private String time;

    /**
     * Construtor que inicializa os dados de dificuldade, valor e tempo.
     * 
     * @param difficulty A dificuldade.
     * @param value O valor.
     * @param time O tempo.
     */
    public AboutData(String difficulty, String value, String time) {
        this.difficulty = difficulty;
        this.value = value;
        this.time = time;
    }

    /**
     * Retorna a dificuldade.
     * 
     * @return A dificuldade.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Retorna o valor.
     * 
     * @return O valor.
     */
    public String getValue() {
        return value;
    }

    /**
     * Retorna o tempo.
     * 
     * @return O tempo.
     */
    public String getTime() {
        return time;
    }
}
