package com.example.family_finance_management.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.family_finance_management.user.User;
import com.example.family_finance_management.user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<TransactionResponseDTO> addTransaction(
            @RequestBody AddTransactionRequestDTO addTransactionRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Fetch the user by email (username)
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's ID
        Long userId = user.getId();
        // Long familyId = user.getFamily().getId();

        TransactionResponseDTO responseDTO = transactionService.addTransaction(addTransactionRequestDTO, userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/family")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByFamilyId(
            @AuthenticationPrincipal UserDetails userDetails) {

        // Get the logged-in user's family ID
        // Fetch the user by email (username)
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the logged-in user's ID
        Long familyId = user.getFamily().getId();

        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByFamilyId(familyId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}