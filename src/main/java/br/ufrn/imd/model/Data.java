package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;

/**
 * Esta classe implementa a interface DataI e fornece implementacoes padrao para os metodos de leitura, carregamento e salvamento de arquivos JSON.
 */
public class Data implements DataI {

    protected String jsonFile;

    /**
     * Construtor que inicializa o nome do arquivo JSON como "Users.json".
     */
    public Data() {
        this.jsonFile = "Users.json";
    }

    /**
     * Define o nome do arquivo JSON.
     * 
     * @param jsonFile O nome do arquivo JSON.
     */
    public void setJsonFile(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    /**
     * Retorna o nome do arquivo JSON.
     * 
     * @return O nome do arquivo JSON.
     */
    public String getJsonFile() {
        return jsonFile;
    }

    /**
     * Metodo vazio para leitura de arquivo JSON.
     */
    @Override
    public void readJsonFile() {}

    /**
     * Metodo vazio para carregar arquivo JSON.
     * 
     * @return null
     */
    @Override
    public UsersList loadJsonFile() {return null;}

    /**
     * Metodo vazio para salvar arquivo JSON.
     * 
     * @param usersList A lista de usuarios a ser salva.
     */
    @Override
    public void saveJsonFile(UsersList usersList) {}
}
