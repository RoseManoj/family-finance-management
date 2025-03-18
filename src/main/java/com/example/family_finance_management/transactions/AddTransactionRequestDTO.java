package com.example.family_finance_management.transactions;

import lombok.Data;

@Data
public class AddTransactionRequestDTO {
    private Double amount;
    private String category;
    private String description;
    private String date; // Date in "yyyy-MM-dd" format
    private TransactionType transactionType;
}