package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe e responsavel por salvar os dados dos usuarios em um arquivo JSON.
 * Extende a classe Data.
 */
public class DataSave extends Data {

    /**
     * Salva a lista de usuarios em um arquivo JSON.
     * 
     * @param usersList A lista de usuarios a ser salva.
     */
    public void saveJsonFile(UsersList usersList) {
        File file = new File(jsonFile);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("");
            fileWriter.flush();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, usersList.getUsers());
            System.out.println("Usuarios salvos com sucesso no arquivo: " + file.getPath());
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
