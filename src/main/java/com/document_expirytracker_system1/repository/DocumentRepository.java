package com.document_expirytracker_system1.repository;

import com.document_expirytracker_system1.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {


    // Custom method to find documents whose expiry date is before the given date
   // List<Document> findByExpiryDateBefore(LocalDate date);

    // You can add more methods later as needed, like filtering by category,etc.
    @Query("SELECT d FROM Document d WHERE LOWER(d.documentName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(d.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Document> searchByDocumentNameOrEmail(@Param("keyword") String keyword);

    List<Document> findByExpiryDateBefore(LocalDate date);
    List<Document> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);
}

