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
        String route1 = "/SpringBackend/Backend/demo/src/main/java/com/example/demo/Controller.java";
        if (new File(route1).exists()) {
            result += "Route 1 exists\n";
        } else {
            result += "Route 1 does not exist\n";
        }
        String route2 = "/Backend/demo/src/main/java/com/example/demo/Data.java";
        if (new File(route2).exists()) {
            result += "Route 2 exists\n";
        } else {
            result += "Route 2 does not exist\n";
        }
        String route3 = "/demo/src/main/java/com/example/demo/Data.java";
        if (new File(route3).exists()) {
            result += "Route 3 exists\n";
        } else {
            result += "Route 3 does not exist\n";
        }
        String route4 = "../demo/Controller.java";
        if (new File(route4).exists()) {
            result += "Route 4 exists\n";
        } else {
            result += "Route 4 does not exist\n";
        }
        //test routes that do not exist
        String route5 = "/SpringBackend/Backend/demo/src/main/java/com/example/demo/Controller.java";
        if (new File(route5).exists()) {
            result += "Route 5 exists\n";
        } else {
            result += "Route 5 does not exist\n";
        }
        String route6 = "/Backend/demo/src/main/java/com/example/demo/Data.java";
        if (new File(route6).exists()) {
            result += "Route 6 exists\n";
        } else {
            result += "Route 6 does not exist\n";
        }
        String route7 = "/demo/src/main/java/com/example/demo/Data.java";
        if (new File(route7).exists()) {
            result += "Route 7 exists\n";
        } else {
            result += "Route 7 does not exist\n";
        }
        String route8 = "../demo/Controller.java";
        if (new File(route8).exists()) {
            result += "Route 8 exists\n";
        } else {
            result += "Route 8 does not exist\n";
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
