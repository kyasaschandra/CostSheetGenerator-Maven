package com.anvitaservices.costsheetgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anvitaservices.costsheetgenerator.model.entity.Details;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
    
    // Find by payment type
    List<Details> findByPayType(String payType);
    
    // Find by base price range
    List<Details> findByBasePrice(BigDecimal BasePrice);
    
    // Find by valid through date
    List<Details> findByValidThru(LocalDate validThru);
    
    // Find details valid after specific date
    List<Details> findByValidThruAfter(LocalDate date);
    
    // Find details updated by specific admin
    List<Details> findByUpdatedBy(Long updatedBy);
    
    // Find details updated after specific date
    List<Details> findByUpdatedOnAfter(LocalDateTime date);
    
    // Find by GST percentage
    List<Details> findByGstPercent(BigDecimal gstPercent);
    
    // Find active details (valid through future dates)
    @Query("SELECT d FROM Details d WHERE d.validThru >= :currentDate")
    List<Details> findActiveDetails(@Param("currentDate") LocalDate currentDate);
    
    // Find expired details
    @Query("SELECT d FROM Details d WHERE d.validThru < :currentDate")
    List<Details> findExpiredDetails(@Param("currentDate") LocalDate currentDate);
    
    // Find latest details (most recent updates)
    @Query("SELECT d FROM Details d ORDER BY d.updatedOn DESC")
    List<Details> findLatestDetails();
    
    // Custom query for pricing calculation
    @Query("SELECT d FROM Details d WHERE d.basePrice <= :maxBudget AND d.validThru >= :currentDate")
    List<Details> findAffordableDetails(@Param("maxBudget") BigDecimal maxBudget, 
                                        @Param("currentDate") LocalDate currentDate);
}