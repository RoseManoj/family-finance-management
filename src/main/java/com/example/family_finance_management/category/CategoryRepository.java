package com.example.family_finance_management.category;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByFamilyId(Long familyId); // Find categories by family ID
}