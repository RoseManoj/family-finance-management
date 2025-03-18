package com.example.family_finance_management.category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(AddCategoryRequestDTO addCategoryRequestDTO, Long familyId);

    List<CategoryResponseDTO> getCategoriesByFamilyId(Long familyId);
}