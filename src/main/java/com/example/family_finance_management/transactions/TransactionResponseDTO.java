package com.example.family_finance_management.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private Double amount;
    private String category;
    private String description;
    private String date;
    private TransactionType transactionType;
}