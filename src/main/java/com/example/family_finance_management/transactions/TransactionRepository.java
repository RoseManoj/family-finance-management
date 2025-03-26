package com.example.family_finance_management.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFamilyId(Long familyId); // Find transactions by family ID

    @Query("SELECT new com.example.transactions.TransactionResponseDTO(t) " +
            "FROM transactions t " +
            "WHERE t.family.id = :familyId " +
            "AND YEAR(t.date) = :year " +
            "AND MONTH(t.date) = :month")
    List<TransactionResponseDTO> findByFamilyIdAndYearAndMonth(@Param("familyId") Long familyId,
            @Param("year") int year, @Param("month") int month);
}