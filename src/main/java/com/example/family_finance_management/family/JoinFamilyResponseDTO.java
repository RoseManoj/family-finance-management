package com.example.family_finance_management.family;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinFamilyResponseDTO {
    private Long id;
    private String familyName;
}