package com.example.family_finance_management.family;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFamilyResponseDTO {
    private Long familyId;
    private String familyCode;
    private String familyName;
}