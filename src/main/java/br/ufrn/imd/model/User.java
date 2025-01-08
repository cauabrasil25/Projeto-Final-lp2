package br.ufrn.imd.model;

/**
 * this class represents a user on the game
 * it contains name, password and the user points
 */
public class User {

    private String username;
    private String password;
    private int maxScore;

    public User() {
        this.username = "";
        this.password = "";
        this.maxScore = 0;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
