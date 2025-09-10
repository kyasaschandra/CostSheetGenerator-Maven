package com.anvitaservices.costsheetgenerator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Long cusId;
    
    @Column(name = "cus_name")
    private String cusName;
    
    @Column(name = "cus_email")
    private String cusEmail;
    
    @Column(name = "cus_phone")
    private String cusPhone;
    
    @Column(name = "cus_address")
    private String cusAddress;
    
    // One-to-many relationship with CustomerSearch
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerSearch> customerSearches;
}