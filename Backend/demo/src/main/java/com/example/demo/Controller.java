package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/modify")
    public HttpStatus update(@RequestBody String body) {
        Gson gson = new Gson();
        Data data = gson.fromJson(body, Data.class);

        data.update();
        
        return HttpStatus.OK;
    }
    
    

    
}
