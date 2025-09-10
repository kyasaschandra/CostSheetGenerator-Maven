package com.anvitaservices.costsheetgenerator.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class EntityScanner implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========== JPA ENTITY SCAN RESULTS ==========");
        
        try {
            var metamodel = entityManager.getMetamodel();
            var entities = metamodel.getEntities();
            
            if (entities.isEmpty()) {
                System.out.println("❌ NO ENTITIES DETECTED!");
                System.out.println("Check:");
                System.out.println("1. @Entity annotation on classes");
                System.out.println("2. @EntityScan package path");
                System.out.println("3. Package structure");
            } else {
                System.out.println("✅ Found " + entities.size() + " entities:");
                entities.forEach(entity -> {
                    System.out.println("  - " + entity.getName() + " (" + entity.getJavaType().getSimpleName() + ")");
                });
            }
        } catch (Exception e) {
            System.out.println("❌ Error scanning entities: " + e.getMessage());
        }
        
        System.out.println("============================================\n");
    }
}