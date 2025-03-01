package com.example.family_finance_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FamilyFinanceManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(FamilyFinanceManagementApplication.class, args);
    }
}

@RestController
class HelloController {
    @GetMapping("/")  // <-- This maps to "http://localhost:8080/"
    public String sayHello() {
        return "Hello!";
    }
}
