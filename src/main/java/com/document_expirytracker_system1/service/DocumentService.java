package com.document_expirytracker_system1.service;

import com.document_expirytracker_system1.model.Document;

import java.time.LocalDate;
import java.util.List;

public interface DocumentService {

    Document saveDocument(Document document);
    List<Document> getAllDocuments();
    Document getDocumentById(Long id);
    void deleteDocument(Long id);
    List<Document> searchDocuments(String keyword);
    List<Document> getExpiredDocuments(LocalDate today);
    List<Document> getDocumentsExpiringWithin(LocalDate from, LocalDate to);

}
