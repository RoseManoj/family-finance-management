package com.example.family_finance_management.family;

import lombok.Data;

@Data
public class UpdateFamilyIncomeRequestDTO {
    private Long familyId; // ID of the family to update
    private Double newIncome; // New income value
}