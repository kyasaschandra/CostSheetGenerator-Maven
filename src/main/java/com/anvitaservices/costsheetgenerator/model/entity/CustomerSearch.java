package com.anvitaservices.costsheetgenerator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_search")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "project")
    private String project;
    
    @Column(name = "tower")
    private String tower;
    
    @Column(name = "floor")
    private Integer floor;
    
    @Column(name = "unit")
    private String unit;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "sft")
    private Double sft;
    
    @Column(name = "face")
    private String face;
    
    @Column(name = "payment_mode")
    private String paymentMode;
    
    @Column(name = "clp_percent_first", precision = 5, scale = 2)
    private BigDecimal clpPercentFirst;
    
    @Column(name = "clp_percent_rest", precision = 5, scale = 2)
    private BigDecimal clpPercentRest;
    
    @Column(name = "clp_tenure")
    private Integer clpTenure;
    
    @Column(name = "date_searched")
    private LocalDateTime dateSearched;
    
    // FIXED: Use proper ManyToOne relationship without separate cusId field
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cus_id", nullable = false)
    private Customer customer;
}