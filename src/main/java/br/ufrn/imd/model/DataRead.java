package br.ufrn.imd.model;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe e responsavel por ler um arquivo JSON.
 * Caso o arquivo nao exista, ele sera criado.
 * Se houver irregularidades no arquivo, o conteudo sera apagado.
 */
public class DataRead extends Data {

    /**
     * Le o arquivo JSON. Se o arquivo nao existir, ele sera criado.
     * Se houver irregularidades, o conteudo do arquivo sera apagado.
     */
    @Override
    public void readJsonFile() {
        File file = new File(jsonFile);
        try {
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("Arquivo criado: " + file.getPath());
                } else {
                    System.out.println("Arquivo ja existe: " + file.getPath());
                }
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    objectMapper.readTree(file);
                    System.out.println("Arquivo lido com sucesso: " + file.getPath());
                } catch (StreamReadException | DatabindException e) {
                    System.err.println("Irregularidades encontradas no arquivo: " + e.getMessage());
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write("");
                    }
                    System.out.println("Conteudo do arquivo apagado devido a irregularidades.");
                }
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
