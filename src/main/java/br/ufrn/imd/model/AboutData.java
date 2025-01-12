package br.ufrn.imd.model;

public class AboutData {
    private String difficulty;
    private String value;
    private String time;

    public AboutData(String difficulty, String value, String time) {
        this.difficulty = difficulty;
        this.value = value;
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getValue() {
        return value;
    }

    public String getTime() {
        return time;
    }
}