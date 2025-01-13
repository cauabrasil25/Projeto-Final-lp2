package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Esta classe e responsavel por carregar os usuarios a partir de um arquivo JSON.
 * Se o arquivo estiver vazio, sera retornada uma lista vazia.
 */
public class DataLoad extends Data {

    /**
     * Carrega os usuarios a partir de um arquivo JSON.
     * Se o arquivo estiver vazio, retorna uma lista vazia.
     * 
     * @return Lista de usuarios carregados.
     */
    @Override
    public UsersList loadJsonFile() {
        UsersList usersList = new UsersList();
        File file = new File(jsonFile);
        try {
            if (file.length() == 0) {
                System.out.println("Arquivo vazio: " + file.getPath());
                return usersList;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            for (User user : users) {
                usersList.addUser(user);
            }
            System.out.println("Usuarios carregados com sucesso do arquivo: " + file.getPath());
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
        return usersList;
    }
}
