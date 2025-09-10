package com.anvitaservices.costsheetgenerator;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EntityScan("com.anvitaservices.costsheetgenerator.model.entity") 
public class CostSheetGeneratorApplication implements CommandLineRunner{

	@Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
    	System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        SpringApplication.run(CostSheetGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Test database connection
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✅ Database connection successful!");
            System.out.println("Database: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("URL: " + connection.getMetaData().getURL());
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
        }
    }
}
