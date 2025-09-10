package com.anvitaservices.costsheetgenerator.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anvitaservices.costsheetgenerator.model.entity.Role;
import com.anvitaservices.costsheetgenerator.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find by username
    Optional<User> findByUsername(String username);
    
    // Find by email
    Optional<User> findByEmail(String email);
    
    // Find by username or email
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    // Find by role
    List<User> findByRole(Role role);
    
    // Find active users
    List<User> findByIsActiveTrue();
    
    // Find active users by role
    List<User> findByRoleAndIsActiveTrue(Role role);
    
    // Find users created after specific date
    List<User> findByCreatedAtAfter(LocalDateTime date);
    
    // Custom query to find admins who updated units/details
    @Query("SELECT DISTINCT u FROM User u WHERE u.id IN (SELECT DISTINCT ut.updatedBy FROM Units ut WHERE ut.updatedBy IS NOT NULL)")
    List<User> findUsersWhoUpdatedUnits();
    
    @Query("SELECT DISTINCT u FROM Details d WHERE d.id IN (SELECT DISTINCT ut.updatedBy FROM Details dt WHERE dt.updatedBy IS NOT NULL)")
    List<User> findUsersWhoUpdatedDetails();
    
    // Check if username exists
    boolean existsByUsername(String username);
    
    // Check if email exists
    boolean existsByEmail(String email);
}
