package com.anvitaservices.costsheetgenerator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "units")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Units {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "project")
    private String project;
    
    @Column(name = "tower")
    private Character tower;
    
    @Column(name = "floor")
    private Integer floor;
    
    @Column(name = "unit")
    private Integer unit;
    
    @Column(name = "sft")
    private Integer sft;
    
    @Column(name = "face")
    private String face;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "corner")
    private Boolean corner;
    
    @Column(name = "view")
    private String view;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
    @Column(name = "updated_by")
    private Long updatedBy; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", insertable = false, updatable = false)
    private User updatedByAdmin;
}
