package com.example.family_finance_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class FamilyFinanceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyFinanceManagementApplication.class, args);
    }
}

record Greeting(Long id, String content, boolean error) {
}

@RestController
class HelloController {

    public final AtomicLong count = new AtomicLong();

    @GetMapping("/") // <-- This maps to "http://localhost:8080/"
    public Greeting sayHello() {
        return new Greeting(count.incrementAndGet(), "Hello World", false);
    }
}
