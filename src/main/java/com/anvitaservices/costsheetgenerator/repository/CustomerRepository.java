package com.anvitaservices.costsheetgenerator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anvitaservices.costsheetgenerator.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Find by email
    Optional<Customer> findByCusEmail(String cusEmail);
    
    // Find by phone
    Optional<Customer> findByCusPhone(String cusPhone);
    
    // Find by name (partial match)
    List<Customer> findByCusNameContainingIgnoreCase(String cusName);
    
    // Find by email or phone
    Optional<Customer> findByCusEmailOrCusPhone(String cusEmail, String cusPhone);
    
    // Find customers with search history
    @Query("SELECT DISTINCT c FROM Customer c WHERE c.cusId IN (SELECT cs.cusId FROM CustomerSearch cs)")
    List<Customer> findCustomersWithSearchHistory();
    
    // Find customers by city/location in address
    List<Customer> findByCusAddressContainingIgnoreCase(String location);
    
    // Check if email exists
    boolean existsByCusEmail(String cusEmail);
    
    // Check if phone exists
    boolean existsByCusPhone(String cusPhone);
}
