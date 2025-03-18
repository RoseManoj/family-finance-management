package com.example.family_finance_management.transactions;

import com.example.family_finance_management.family.*;
import com.example.family_finance_management.user.User;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String category; // e.g., groceries, utilities, entertainment

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private LocalDate date; // Date of the transaction

    @ManyToOne
    @JoinColumn(name = "family_member_id", nullable = false)
    private User familyMember; // The family member who performed the transaction

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private Family family; // The family associated with the transaction
}