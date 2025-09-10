package com.anvitaservices.costsheetgenerator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "project")
    private String project;
    
    @Column(name = "towers")
    private Integer towers;
    
    @Column(name = "units_left")
    private Integer unitsLeft;
    
    @Column(name = "area_left")
    private Double areaLeft;
    
    @Column(name = "bank_id")
    private Long bankId;
    
    @Column(name = "beneficiary_name")
    private String beneficiaryName;
    
    // Many-to-one relationship with BankDetails
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", insertable = false, updatable = false)
    private BankDetails bankDetails;
}
