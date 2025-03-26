package com.example.family_finance_management.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.family_finance_management.category.CategoryService;
import com.example.family_finance_management.user.User;
import com.example.family_finance_management.user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(
            @RequestBody AddTransactionRequestDTO addTransactionRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Fetch the user by email (username)
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's ID
        Long userId = user.getId();

        if (addTransactionRequestDTO.getTransactionType() == TransactionType.EXPENSE) {
            // Extract category ID and amount from the request DTO
            Long categoryId = addTransactionRequestDTO.getCategoryId();
            Double amount = addTransactionRequestDTO.getAmount();

            try {
                categoryService.increaseSpent(categoryId, amount);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        TransactionResponseDTO responseDTO = transactionService.addTransaction(addTransactionRequestDTO, userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/family")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByFamilyId(
            @AuthenticationPrincipal UserDetails userDetails) {

        // Fetch the user by email (username)
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's family ID
        Long familyId = user.getFamily().getId();

        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByFamilyId(familyId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/month")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Fetch the user by email (username)
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's family ID
        Long familyId = user.getFamily().getId();

        // Fetch transactions by family ID, year, and month
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByFamilyIdAndMonth(familyId, year,
                month);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}