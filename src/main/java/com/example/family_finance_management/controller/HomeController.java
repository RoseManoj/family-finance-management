package com.example.family_finance_management.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "Welcome to Family Finance!";
    }
}
