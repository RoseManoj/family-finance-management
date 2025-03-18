package com.example.family_finance_management.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProtectedController {

    @GetMapping()
    public String ProtectedEndpoint() {
        return "protected endpoint!";
    }
}