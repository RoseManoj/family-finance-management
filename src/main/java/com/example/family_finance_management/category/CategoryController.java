package com.example.family_finance_management.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.family_finance_management.family.FamilyRepository;
import com.example.family_finance_management.user.User;
import com.example.family_finance_management.user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDTO> addCategory(
            @RequestBody AddCategoryRequestDTO addCategoryRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's ID
        Long familyId = user.getFamily().getId();

        CategoryResponseDTO responseDTO = categoryService.addCategory(addCategoryRequestDTO, familyId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/family")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoriesByFamilyId(
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's ID
        Long familyId = user.getFamily().getId();

        List<CategoryResponseDTO> categories = categoryService.getCategoriesByFamilyId(familyId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}