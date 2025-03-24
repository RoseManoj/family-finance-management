package com.example.family_finance_management.family;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFamilyIncomeResponseDTO {
    private Long familyId; // ID of the updated family
    private Double familyIncome; // Updated income value
}