package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;


public class Data {
    public int Id;
    public String Name;
    public String Fullname;
    public String Phone;
    public String Email;
    public String Company;
    public String Media;
    public String Sector;
    public String Position;
    public String PersonalAddress;
    public String ProfessionalAddress;
    public String Country;
    public String Community;
    public String Region;
    public String Observations;
    public String SocialMedia;
    public String Clients;

    public Data(int id, String name,String fullname ,String phone, String email, String company, String media, String sector, String position, String personalAddress, String professionalAddress, String country, String community, String region, String observations, String socialMedia, String clients) {
        this.Id = id;
        this.Name = name;
        this.Fullname = fullname;
        this.Phone = phone;
        this.Email = email;
        this.Company = company;
        this.Media = media;
        this.Sector = sector;
        this.Position = position;
        this.PersonalAddress = personalAddress;
        this.ProfessionalAddress = professionalAddress;
        this.Country = country;
        this.Community = community;
        this.Region = region;
        this.Observations = observations;
        this.SocialMedia = socialMedia;
        this.Clients = clients;
    }

    public void save() {
        
        String projectRoute = System.getProperty("user.dir");
        String route = projectRoute + "/src/main/resources/testResources/database.csv";

        try {
            //open the file and get the highest value of the first column of the csv
            FileReader fileReader = new FileReader(route);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int maxId = 0;
            while (line != null) {
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                if (id > maxId) {
                    maxId = id;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            
            //write the new data
            this.Id = maxId + 1;
            FileWriter fileWriter = new FileWriter(route, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(this.Id + ";" + this.Name + ";" +this.Fullname + ";" + this.Phone + ";" + this.Email + ";" + this.Company + ";" + this.Media + ";" + this.Sector + ";" + this.Position + ";" + this.PersonalAddress + ";" + this.ProfessionalAddress + ";" + this.Country + ";" + this.Community + ";" + this.Region + ";" + this.Observations + ";" + this.SocialMedia + ";" + this.Clients);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        String projectRoute = System.getProperty("user.dir");
        String route = projectRoute + "/src/main/resources/testResources/database.csv";
        try {
            //open the file and get the highest value of the first column of the csv
            FileReader fileReader = new FileReader(route);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String newFile = "";
            while (line != null) {
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                if (id == this.Id) {
                    newFile += this.Id + ";" + this.Name + ";" +this.Fullname + ";" + this.Phone + ";" + this.Email + ";" + this.Company + ";" + this.Media + ";" + this.Sector + ";" + this.Position + ";" + this.PersonalAddress + ";" + this.ProfessionalAddress + ";" + this.Country + ";" + this.Community + ";" + this.Region + ";" + this.Observations + ";" + this.SocialMedia + ";" + this.Clients + "\n";
                } else {
                    newFile += line + "\n";
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            FileWriter fileWriter = new FileWriter(route);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(newFile);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
