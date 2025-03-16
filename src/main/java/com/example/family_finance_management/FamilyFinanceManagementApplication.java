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
    @GetMapping("/")  // Maps to "http://localhost:8080/"
    public String sayHello() {
        return "Hellooo!";
    }

    @GetMapping("/api/data")  // New endpoint for your frontend to call
    public String getData() {
        return "{\"message\": \"This is data from the backend!\"}";
    }
}
