package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;

public class Data implements DataI {

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

    @Override
    public void readJsonFile() {}

    @Override
    public UsersList loadJsonFile() {return null;}

    @Override
    public void saveJsonFile(UsersList usersList) {}
}
