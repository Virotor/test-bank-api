package com.bank.testbankapi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping(path = "/message")
    public ResponseEntity<?> getMessage(){
        return  ResponseEntity.ok("Hello world");
    } 

    
}
