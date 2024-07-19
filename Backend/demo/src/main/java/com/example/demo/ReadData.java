package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;



public class ReadData {

    public String readData(){
         
        String projectRoot = System.getProperty("user.dir");
        String dataPath = projectRoot + "/Backend/demo/src/main/resources/testResources/database.csv";

        try (FileReader filereader = new FileReader(dataPath);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                                    .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build())
                                    .build()) {

            // Reading all records at once
            List<String[]> allData = csvReader.readAll();
            List<Data> dataList = new ArrayList<Data>();

            for (String[] row : allData) {
                int row0 = Integer.parseInt(row[0]);
                Data data = new Data(row0, row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], row[14], row[15], row[16]);
                dataList.add(data);
            }

            //convert to json
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            JsonArray jsonArray = gson.toJsonTree(dataList).getAsJsonArray();

            return jsonArray.toString(); 

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteById(int id){
        String projectRoot = System.getProperty("user.dir");
        String dataPath = projectRoot + "/Backend/demo/src/main/resources/testResources/database.csv";

        String idToDelete = Integer.toString(id);

        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(dataPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by semicolon to get the columns
                String[] columns = line.split(";");
                // Check if the first column matches the ID to delete
                if (!columns[0].equals(idToDelete)) {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(dataPath))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
