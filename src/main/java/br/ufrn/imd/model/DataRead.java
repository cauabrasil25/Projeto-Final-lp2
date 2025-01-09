package br.ufrn.imd.model;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataRead extends Data {

    @Override
    public void readJsonFile() {
        File file = new File(jsonFile);
        try {
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("File created: " + file.getPath());
                } else {
                    System.out.println("File already exists: " + file.getPath());
                }
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    objectMapper.readTree(file);
                    System.out.println("File read successfully: " + file.getPath());
                } catch (StreamReadException | DatabindException e) {
                    System.err.println("Irregularities found in the file: " + e.getMessage());
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write("");
                    }
                    System.out.println("File content cleared due to irregularities.");
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
