package com.example.family_finance_management.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFamilyId(Long familyId); // Find transactions by family ID
}