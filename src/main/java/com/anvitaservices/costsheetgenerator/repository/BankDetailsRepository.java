package com.anvitaservices.costsheetgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anvitaservices.costsheetgenerator.model.entity.BankDetails;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
    
    // Find by bank name
    Optional<BankDetails> findByBankName(String bankName);
    
    // Find by bank name containing (partial match)
    List<BankDetails> findByBankNameContainingIgnoreCase(String bankName);
    
    // Find banks with projects
    @Query("SELECT DISTINCT b FROM BankDetails b WHERE b.bankId IN (SELECT p.bankId FROM Project p)")
    List<BankDetails> findBanksWithProjects();
    
    // Check if bank name exists
    boolean existsByBankName(String bankName);
}