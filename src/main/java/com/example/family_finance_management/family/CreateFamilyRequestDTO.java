package com.example.family_finance_management.family;

import lombok.Data;

@Data
public class CreateFamilyRequestDTO {
    private String familyName;
    private int memberCount;
}