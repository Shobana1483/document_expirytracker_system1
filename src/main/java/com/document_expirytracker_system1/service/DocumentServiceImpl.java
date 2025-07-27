package com.document_expirytracker_system1.service;


import com.document_expirytracker_system1.model.Document;
import com.document_expirytracker_system1.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


    @Service
    public class DocumentServiceImpl implements DocumentService {

        @Autowired
        private DocumentRepository documentRepository;

        @Override
        public Document saveDocument(Document document) {
            return documentRepository.save(document);
        }

        @Override
        public List<Document> getAllDocuments() {
            return documentRepository.findAll();
        }

        @Override
        public List<Document>
        searchDocuments(String keyword) {
            return documentRepository.searchByDocumentNameOrEmail(keyword);
        }

        @Override
        public Document getDocumentById(Long id) {
            Optional<Document> optional = documentRepository.findById(id);
            return optional.orElse(null);
        }

        @Override
        public void deleteDocument(Long id) {
            documentRepository.deleteById(id);
        }

        @Override
        public List<Document> getExpiredDocuments(LocalDate today) {
            return documentRepository.findByExpiryDateBefore(today);
        }

        @Override
        public List<Document> getDocumentsExpiringWithin(LocalDate from, LocalDate to) {
            return documentRepository.findByExpiryDateBetween(from,to);
        }


        @Autowired
        private EmailService emailService;
        @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM // @Scheduled(fixed Rate= 60000) => every 60 seconds for testing
        //@Scheduled(fixedRate = 60000)
        public void sendExpiryReminders() {
            List<Document> documents = documentRepository.findAll();

            LocalDate today = LocalDate.now();

            for (Document doc : documents) {
                if (doc.getExpiryDate() != null && doc.getEmail() != null) {
                    long daysLeft = ChronoUnit.DAYS.between(today, doc.getExpiryDate());

                    if (daysLeft == 3) { // Change to desired days
                        String subject = "⏰ Document Expiry Reminder";
                        String body = "Dear User,\n\nYour document \"" + doc.getDocumentName() +
                                "\" will expire in 3 days (on " + doc.getExpiryDate() + "). Please take action.";

                        emailService.sendEmail(doc.getEmail(), subject, body);
                    }
               }
            }
        }

    }

