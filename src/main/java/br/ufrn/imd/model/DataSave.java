package br.ufrn.imd.model;

import br.ufrn.imd.dao.UsersList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataSave extends Data {

    public void saveJsonFile(UsersList usersList) {
        File file = new File(jsonFile);
        try (FileWriter fileWriter = new FileWriter(file)) {
            // Clear the file content
            fileWriter.write("");
            fileWriter.flush();

            // Write users to the file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, usersList.getUsers());
            System.out.println("Users saved successfully to file: " + file.getPath());
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
