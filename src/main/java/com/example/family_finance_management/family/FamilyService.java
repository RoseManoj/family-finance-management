package com.example.family_finance_management.family;

public interface FamilyService {
    CreateFamilyResponseDTO createFamily(CreateFamilyRequestDTO createFamilyRequestDTO, Long familyHeadId);

    JoinFamilyResponseDTO joinFamily(JoinFamilyRequestDTO joinFamilyRequestDTO, Long userId);
}