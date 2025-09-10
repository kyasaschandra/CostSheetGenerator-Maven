package com.anvitaservices.costsheetgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anvitaservices.costsheetgenerator.model.entity.Units;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Long> {
    
    // Find by project
    List<Units> findByProject(String project);
    
    // Find by project and tower
    List<Units> findByProjectAndTower(String project, String tower);
    
    // Find by project, tower, and floor
    List<Units> findByProjectAndTowerAndFloor(String project, String tower, Integer floor);
    
    // Find by status
    List<Units> findByStatus(String status);
    
    // Find by type
    List<Units> findByType(String type);
    
    // Find by face
    List<Units> findByFace(String face);
    
    // Find corner units
    List<Units> findByCornerTrue();
    
    // Find by view
    List<Units> findByView(String view);
    
    // Find by SFT range
    List<Units> findBySftBetween(Double minSft, Double maxSft);
    
    //Find by SFT Value
    List<Units> findBySft(Double Sft);
    
    // Find specific units with Sft
    Optional<Units> findByProjectAndTowerAndFloorAndUnit(String project, String tower, Integer floor, String unit);
    
    // Find specific unit
    Optional<Units> findByProjectAndTowerAndFloorAndSft(String project, String tower, Integer floor, Double Sft);
    
    // Find specific unit
    Optional<Units> findByProjectAndTowerAndSft(String project, String tower, Double Sft);
    
 // Find specific unit
    Optional<Units> findByProjectAndSft(String project, Double Sft);
    
    // Find units updated by specific admin
    List<Units> findByUpdatedBy(Long updatedBy);
    
    // Find units updated after specific date
    List<Units> findByUpdatedOnAfter(LocalDateTime date);
    
    // Custom query for available units (assuming 'AVAILABLE' status)
    @Query("SELECT u FROM Units u WHERE u.status = 'AVAILABLE'")
    List<Units> findAvailableUnits();
    
    // Custom query for units by multiple criteria
    @Query("SELECT u FROM Units u WHERE u.project = :project AND u.type = :type AND u.status = :status")
    List<Units> findByProjectAndTypeAndStatus(@Param("project") String project, 
                                              @Param("type") String type, 
                                              @Param("status") String status);
    
    // Count units by project
    long countByProject(String project);
    
    // Count units by status
    long countByStatus(String status);
}
