package com.example.family_finance_management.family;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.family_finance_management.user.User;
import jakarta.persistence.*;

// Family.java
@Entity
@Table(name = "families")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String familyCode;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<User> members = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // getters, setters
}