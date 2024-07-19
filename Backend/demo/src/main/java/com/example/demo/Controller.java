package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

import com.google.gson.Gson;

@RestController
public class Controller {

    
    @PostMapping("/add")
    public HttpStatus add(@RequestBody String body) {
        Gson gson = new Gson();
        Data data = gson.fromJson(body, Data.class);
        data.save();
        
        return HttpStatus.OK;
    }

    //add another endpoint to delete a record
    @PostMapping("/delete")
    public HttpStatus delete(@RequestBody String body) {

        Gson gson = new Gson();
        DeleteRequest deleteRequest = gson.fromJson(body, DeleteRequest.class);
        ReadData readData = new ReadData();
        System.out.println(deleteRequest.getId());
        readData.deleteById(deleteRequest.getId());
        
        return HttpStatus.OK;
    }

    @GetMapping("/get")
    public String get() {
        ReadData readData = new ReadData();
        return readData.readData();
    }

    @GetMapping("/")
    public String home() {
        //test routes that exist
        String result = "";
        String route9 = "/app/";
        //if directory exists
        if (new File(route9).exists()) {
            result += "Route 9 exists\n";
            //add as well the subdirectories
            //find the subdirectories
            File[] files = new File(route9).listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    result += file.getName() + "\n";
                }
            }
        } else {
            result += "Route 9 does not exist\n";
        }

        

        return result;
    }

    @PutMapping("/modify")
    public HttpStatus update(@RequestBody String body) {
        Gson gson = new Gson();
        Data data = gson.fromJson(body, Data.class);

        data.update();
        
        return HttpStatus.OK;
    }
    
    

    
}
