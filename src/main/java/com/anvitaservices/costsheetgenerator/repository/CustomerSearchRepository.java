package com.anvitaservices.costsheetgenerator.repository;

import com.anvitaservices.costsheetgenerator.model.entity.CustomerSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerSearchRepository extends JpaRepository<CustomerSearch, Long> {
    
    // FIXED: Find by customer ID using relationship
    @Query("SELECT cs FROM CustomerSearch cs WHERE cs.customer.cusId = :cusId")
    List<CustomerSearch> findByCusId(@Param("cusId") Long cusId);
    
    // Find by project
    List<CustomerSearch> findByProject(String project);
    
    // FIXED: Find by customer ID and project using relationship
    @Query("SELECT cs FROM CustomerSearch cs WHERE cs.customer.cusId = :cusId AND cs.project = :project")
    List<CustomerSearch> findByCusIdAndProject(@Param("cusId") Long cusId, @Param("project") String project);
    
    // FIXED: Find by customer ID ordered by date using relationship
    @Query("SELECT cs FROM CustomerSearch cs WHERE cs.customer.cusId = :cusId ORDER BY cs.dateSearched DESC")
    List<CustomerSearch> findByCusIdOrderByDateSearchedDesc(@Param("cusId") Long cusId);
    
    // Find searches after specific date
    List<CustomerSearch> findByDateSearchedAfter(LocalDateTime date);
    
    // Find by payment mode
    List<CustomerSearch> findByPaymentMode(String paymentMode);
    
    // Find by type
    List<CustomerSearch> findByType(String type);
    
    // Find by SFT range
    List<CustomerSearch> findBySftBetween(Double minSft, Double maxSft);
    
    // Recent searches (last 30 days)
    @Query("SELECT cs FROM CustomerSearch cs WHERE cs.dateSearched >= :thirtyDaysAgo ORDER BY cs.dateSearched DESC")
    List<CustomerSearch> findRecentSearches(@Param("thirtyDaysAgo") LocalDateTime thirtyDaysAgo);
    
    // Popular projects (most searched)
    @Query("SELECT cs.project, COUNT(cs) as searchCount FROM CustomerSearch cs GROUP BY cs.project ORDER BY searchCount DESC")
    List<Object[]> findPopularProjects();
    
    // FIXED: Customer's latest search using relationship
    @Query("SELECT cs FROM CustomerSearch cs WHERE cs.customer.cusId = :cusId ORDER BY cs.dateSearched DESC")
    List<CustomerSearch> findLatestSearchesByCustomer(@Param("cusId") Long cusId);
    
    // Search trends by project and date range
    @Query("SELECT cs FROM CustomerSearch cs WHERE cs.project = :project AND cs.dateSearched BETWEEN :startDate AND :endDate")
    List<CustomerSearch> findSearchesByProjectAndDateRange(@Param("project") String project, 
                                                           @Param("startDate") LocalDateTime startDate, 
                                                           @Param("endDate") LocalDateTime endDate);
}