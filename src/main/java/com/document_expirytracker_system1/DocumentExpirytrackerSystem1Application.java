package com.document_expirytracker_system1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = "com.document_expirytracker_system1.model")  // ✅ Correct package for your @Entity class
@ComponentScan(basePackages = "com.document_expirytracker_system1")     // ✅ Scans all components like @Service, @Repository
public class DocumentExpirytrackerSystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(DocumentExpirytrackerSystem1Application.class,args);
    }
}