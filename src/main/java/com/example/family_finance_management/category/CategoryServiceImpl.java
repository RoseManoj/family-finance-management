package com.example.family_finance_management.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.family_finance_management.family.Family;
import com.example.family_finance_management.family.FamilyRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
                category.setBudget(addCategoryRequestDTO.getBudget());
                category.setSpent(0.0);
                // Save the category
                Category savedCategory = categoryRepository.save(category);

                // Return the response
                return new CategoryResponseDTO(savedCategory.getId(), savedCategory.getName(), category.getBudget(),
                                category.getSpent());
        }

        @Override
        public List<CategoryResponseDTO> getCategoriesByFamilyId(Long familyId) {
                // Find all categories for the family
                List<Category> categories = categoryRepository.findByFamilyId(familyId);

                // Convert to DTOs
                return categories.stream()
                                .map(category -> new CategoryResponseDTO(category.getId(), category.getName(),
                                                category.getBudget(),
                                                category.getSpent()))
                                .collect(Collectors.toList());
        }

        @Override
        @Transactional
        public void increaseSpent(Long categoryId, Double amount) throws Exception {
                Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
                if (!optionalCategory.isPresent()) {
                        throw new Exception("Category not found");
                }

                Category category = optionalCategory.get();
                Double newSpent = category.getSpent() + amount;

                if (newSpent > category.getBudget()) {
                        throw new Exception("Spent amount exceeds the allocated budget");
                }

                category.setSpent(newSpent);
                categoryRepository.save(category);
        }
}