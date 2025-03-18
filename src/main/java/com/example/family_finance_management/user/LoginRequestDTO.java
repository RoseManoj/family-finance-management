package com.example.family_finance_management.user;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}