package com.ouz.k8sexample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Value("${welcome.greeting}")
    private String greetingName;

    @GetMapping("/say-hi")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi. it's me, an application. Nice to see you.\n Aaand your name is : "+greetingName+" , right?");
    }
}
