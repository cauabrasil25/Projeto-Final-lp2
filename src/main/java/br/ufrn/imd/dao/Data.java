package br.ufrn.imd.dao;

public class Data  implements DataI{

    protected String jsonFile;

    public Data() {
        this.jsonFile = "../../../../../Users.json";
    }

    public void setJsonFile(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public String getJsonFile() {
        return jsonFile;
    }

    public void openFile() {

    }

    public void closeFile() {

    }
}
