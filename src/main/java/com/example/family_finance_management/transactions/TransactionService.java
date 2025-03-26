package com.example.family_finance_management.transactions;

import java.util.List;

public interface TransactionService {
    TransactionResponseDTO addTransaction(AddTransactionRequestDTO addTransactionRequestDTO, Long userId);

    List<TransactionResponseDTO> getTransactionsByFamilyId(Long familyId);

    List<TransactionResponseDTO> getTransactionsByFamilyIdAndMonth(Long familyId, int year, int month);
}
