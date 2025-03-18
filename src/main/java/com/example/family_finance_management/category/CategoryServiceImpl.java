package com.example.family_finance_management.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.family_finance_management.family.Family;
import com.example.family_finance_management.family.FamilyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public CategoryResponseDTO addCategory(AddCategoryRequestDTO addCategoryRequestDTO, Long familyId) {
        // Find the family
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new RuntimeException("Family not found"));

        // Create the category
        Category category = new Category();
        category.setName(addCategoryRequestDTO.getName());
        category.setFamily(family);

        // Save the category
        Category savedCategory = categoryRepository.save(category);

        // Return the response
        return new CategoryResponseDTO(savedCategory.getId(), savedCategory.getName());
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesByFamilyId(Long familyId) {
        // Find all categories for the family
        List<Category> categories = categoryRepository.findByFamilyId(familyId);

        // Convert to DTOs
        return categories.stream()
                .map(category -> new CategoryResponseDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }
}