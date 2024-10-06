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

    @Value("${welcome.greeting}")
    private String greeting;

    @Value("${welcome.message}")
    private String greetingMessage;

    @GetMapping("/say-hi-to")
    public ResponseEntity<String> sayHello(@RequestParam String name){
        return ResponseEntity.ok("Hi. it's me, the application. Nice to see you." +
                "\nAnd "+greeting+" to you,"+name);
    }

    @PostConstruct
    public void init() {
        System.out.println("Application started with message: " + greetingMessage);
    }
}
