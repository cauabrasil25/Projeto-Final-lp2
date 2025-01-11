package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataLoad extends Data {

    @Override
    public UsersList loadJsonFile() {
        UsersList usersList = new UsersList();
        File file = new File(jsonFile);
        try {
            if (file.length() == 0) {
                System.out.println("File is blank: " + file.getPath());
                return usersList;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            for (User user : users) {
                usersList.addUser(user);
            }
            System.out.println("Users loaded successfully from file: " + file.getPath());
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return usersList;
    }
}
