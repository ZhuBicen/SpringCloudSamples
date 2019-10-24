package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/admin/hello")
    String adminHello() {
        return "Hello admin";
    }


    @GetMapping("/user/hello")
    String userHello() {
        return "Hello user";
    }
}
