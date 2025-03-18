package com.example.family_finance_management.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.family_finance_management.category.Category;
import com.example.family_finance_management.category.CategoryRepository;
import com.example.family_finance_management.family.Family;
import com.example.family_finance_management.family.FamilyRepository;
import com.example.family_finance_management.user.User;
import com.example.family_finance_management.user.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

        @Autowired
        private TransactionRepository transactionRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Override
        public TransactionResponseDTO addTransaction(AddTransactionRequestDTO addTransactionRequestDTO, Long userId) {
                // Find the family
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Find the category
                Category category = categoryRepository.findById(addTransactionRequestDTO.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found"));

                // Create the transaction
                Transaction transaction = new Transaction();
                transaction.setAmount(addTransactionRequestDTO.getAmount());
                transaction.setCategory(category); // Use Category entity
                transaction.setDescription(addTransactionRequestDTO.getDescription());
                transaction.setDate(LocalDate.parse(addTransactionRequestDTO.getDate())); // Parse date from string
                transaction.setFamily(user.getFamily());
                transaction.setFamilyMember(user);
                transaction.setTransactionType(addTransactionRequestDTO.getTransactionType());

                // Save the transaction
                Transaction savedTransaction = transactionRepository.save(transaction);

                // Return the response
                return new TransactionResponseDTO(
                                savedTransaction.getId(),
                                savedTransaction.getAmount(),
                                savedTransaction.getCategory().getName(), // Use category name
                                savedTransaction.getDescription(),
                                savedTransaction.getDate().toString(),
                                savedTransaction.getTransactionType());
        }

        @Override
        public List<TransactionResponseDTO> getTransactionsByFamilyId(Long familyId) {
                // Find all transactions for the family
                List<Transaction> transactions = transactionRepository.findByFamilyId(familyId);

                // Convert to DTOs
                return transactions.stream()
                                .map(transaction -> new TransactionResponseDTO(
                                                transaction.getId(),
                                                transaction.getAmount(),
                                                transaction.getCategory().getName(), // Use category name
                                                transaction.getDescription(),
                                                transaction.getDate().toString(),
                                                transaction.getTransactionType()))
                                .collect(Collectors.toList());
        }
}