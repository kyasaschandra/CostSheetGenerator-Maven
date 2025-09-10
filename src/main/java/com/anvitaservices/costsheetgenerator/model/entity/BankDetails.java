package com.anvitaservices.costsheetgenerator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "bank_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;
    
    @Column(name = "bank_name")
    private String bankName;
    
    // One-to-many relationship with Project
    @OneToMany(mappedBy = "bankDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects;
}
