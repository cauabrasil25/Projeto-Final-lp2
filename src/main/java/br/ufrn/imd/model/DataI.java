package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;

/**
 * Esta interface define os metodos para leitura, carregamento e salvamento de arquivos JSON.
 */
public interface DataI {

    /**
     * Lê um arquivo JSON.
     */
    public void readJsonFile();

    /**
     * Carrega um arquivo JSON e retorna uma lista de usuarios.
     * 
     * @return Lista de usuarios carregados.
     */
    public UsersList loadJsonFile();

    /**
     * Salva uma lista de usuarios em um arquivo JSON.
     * 
     * @param usersList A lista de usuarios a ser salva.
     */
    public void saveJsonFile(UsersList usersList);
}
