package com.anvitaservices.costsheetgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anvitaservices.costsheetgenerator.model.entity.UserSession;
import com.anvitaservices.costsheetgenerator.model.entity.UserType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    
    // Find by session token
    Optional<UserSession> findBySessionToken(String sessionToken);
    
    // Find active session by token
    Optional<UserSession> findBySessionTokenAndIsActiveTrue(String sessionToken);
    
    // Find by refresh token
    Optional<UserSession> findByRefreshToken(String refreshToken);
    
    // Find active sessions by user ID
    List<UserSession> findByUserIdAndIsActiveTrue(Long userId);
    
    // Find sessions by user type
    List<UserSession> findByUserType(UserType userType);
    
    // Find active client sessions
    List<UserSession> findByUserTypeAndIsActiveTrue(UserType userType);
    
    // Find sessions by client email
    List<UserSession> findByClientEmail(String clientEmail);
    
    // Find expired sessions
    @Query("SELECT s FROM UserSession s WHERE s.expiresAt < :currentTime")
    List<UserSession> findExpiredSessions(@Param("currentTime") LocalDateTime currentTime);
    
    // Find sessions by IP address
    List<UserSession> findByIpAddress(String ipAddress);
    
    // Deactivate all sessions for a user
    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.isActive = false WHERE s.userId = :userId")
    int deactivateAllSessionsByUserId(@Param("userId") Long userId);
    
    // Deactivate expired sessions
    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.isActive = false WHERE s.expiresAt < :currentTime AND s.isActive = true")
    int deactivateExpiredSessions(@Param("currentTime") LocalDateTime currentTime);
    
    // Update last accessed time
    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.lastAccessed = :lastAccessed WHERE s.sessionToken = :token")
    int updateLastAccessed(@Param("token") String token, @Param("lastAccessed") LocalDateTime lastAccessed);
}