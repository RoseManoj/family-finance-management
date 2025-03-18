package com.example.family_finance_management.category;

import com.example.family_finance_management.family.Family;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., groceries, utilities, entertainment

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family; // Optional: Associate category with a family
}