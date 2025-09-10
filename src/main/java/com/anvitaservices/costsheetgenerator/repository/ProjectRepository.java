package com.anvitaservices.costsheetgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anvitaservices.costsheetgenerator.model.entity.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // Find by project name
    Optional<Project> findByProject(String project);
    
    // Find by bank ID
    List<Project> findByBankId(Long bankId);
    
    // Find projects with units available
    List<Project> findByUnitsLeftGreaterThan(Integer unitsLeft);
    
    // Find projects with area available
    List<Project> findByAreaLeftGreaterThan(Double areaLeft);
    
    // Find by beneficiary name
    List<Project> findByBeneficiaryNameContainingIgnoreCase(String beneficiaryName);
    
    // Find projects by tower count
    List<Project> findByTowers(Integer towers);
    
    // Find projects by tower range
    List<Project> findByTowersBetween(Integer minTowers, Integer maxTowers);
    
    // Custom query for available projects
    @Query("SELECT p FROM Project p WHERE p.unitsLeft > 0 AND p.areaLeft > 0")
    List<Project> findAvailableProjects();
    
    // Projects with specific bank
    @Query("SELECT p FROM Project p JOIN p.bankDetails b WHERE b.bankName = :bankName")
    List<Project> findProjectsByBankName(@Param("bankName") String bankName);
    
    // Check if project name exists
    boolean existsByProject(String project);
}
