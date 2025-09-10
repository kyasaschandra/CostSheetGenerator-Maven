package com.anvitaservices.costsheetgenerator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "base_price", precision = 15, scale = 2)
    private BigDecimal basePrice;
    
    @Column(name = "pay_type")
    private String payType;
    
    @Column(name = "valid_thru")
    private LocalDate validThru;
    
    @Column(name = "corner_charge", precision = 15, scale = 2)
    private BigDecimal cornerCharge;
    
    @Column(name = "floorrise_charge", precision = 15, scale = 2)
    private BigDecimal floorriseCharge;
    
    @Column(name = "view_charge", precision = 15, scale = 2)
    private BigDecimal viewCharge;
    
    @Column(name = "face_charge", precision = 15, scale = 2)
    private BigDecimal faceCharge;
    
    @Column(name = "amenities_charge", precision = 15, scale = 2)
    private BigDecimal amenitiesCharge;
    
    @Column(name = "infra_charge", precision = 15, scale = 2)
    private BigDecimal infraCharge;
    
    @Column(name = "parking_charge", precision = 15, scale = 2)
    private BigDecimal parkingCharge;
    
    @Column(name = "default_parking_lots")
    private Integer defaultParkingLots;
    
    @Column(name = "gst_percent", precision = 5, scale = 2)
    private BigDecimal gstPercent;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
    @Column(name = "updated_by")
    private Long updatedBy; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", insertable = false, updatable = false)
    private User updatedByAdmin;
}