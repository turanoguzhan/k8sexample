package com.ouz.k8sexample.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Value("${greeting}")
    private String greeting;

    @Value("${message}")
    private String message;

    @GetMapping("/")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok(message);
    }

    @GetMapping("/say-hi")
    public ResponseEntity<String> hello(@RequestParam String name){
        return ResponseEntity.ok(greeting +" "+name+",\nit's me, the application. Nice to see you.");
    }

    @PostConstruct
    public void init() {
        System.out.println("Application started with message: " + message);
    }
}
