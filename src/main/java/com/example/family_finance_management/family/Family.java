package com.example.family_finance_management.family;

import java.time.LocalDateTime;

import com.example.family_finance_management.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String familyCode; // Unique code for joining the family

    @Column(nullable = false, name = "member_count")
    private int memberCount;

    @Column(nullable = false)
    private String familyName;

    @OneToOne
    @JoinColumn(name = "family_head_id", nullable = false)
    private User familyHead; // The user who created the family

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}