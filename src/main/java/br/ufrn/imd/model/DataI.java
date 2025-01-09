package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;

public interface DataI {

    public void readJsonFile();

    public UsersList loadJsonFile();

    public void saveJsonFile(UsersList usersList);
}
