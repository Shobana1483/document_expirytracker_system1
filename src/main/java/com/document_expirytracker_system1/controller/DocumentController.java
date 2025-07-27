package com.document_expirytracker_system1.controller;

import com.document_expirytracker_system1.model.Document;
import com.document_expirytracker_system1.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller // ✅ Changed from @RestController to @Controller
@RequestMapping("/documents")
@CrossOrigin("*")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // ✅ This is for Postman (Insert)
    @PostMapping
    @ResponseBody
    public Document createDocument(@RequestBody Document document) {
        return documentService.saveDocument(document);
    }

    // ✅ This is for Postman (Get all)
    @GetMapping
    @ResponseBody
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    // ✅ This is for Postman (Get by ID)
    @GetMapping("/{id}")
    @ResponseBody
    public Document getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }

    // ✅ This is for Postman (Delete)
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }

    // ✅ This is for browser HTML page
    /*@GetMapping("/document_list")
    public String showDocumentListPage(Model model) {
        List<Document> documents = documentService.getAllDocuments();
        model.addAttribute("documents", documents);
        return "document_list"; // it will look for document_list.html inside templates
    }*/

    /*@GetMapping("/document_list")
    public String showDocumentListPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Document> documents;

        if (keyword != null && !keyword.isEmpty()) {
            documents = documentService.searchDocuments(keyword);
        } else {
            documents = documentService.getAllDocuments();
        }

        model.addAttribute("documents", documents);
        model.addAttribute("keyword", keyword); // to retain value in search box
        return "document_list";
    }*/

    /*@GetMapping("/document_list")
    public String showDocumentListPage(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sortField", required = false) String sortField,
            Model model) {

        List<Document> documents;

        if (keyword != null && !keyword.isEmpty()) {
            documents = documentService.searchDocuments(keyword);
        } else {
            documents = documentService.getAllDocuments();
        }

        // Sort logic
        if ("documentName".equals(sortField)) {
            documents.sort(Comparator.comparing(Document::getDocumentName, String.CASE_INSENSITIVE_ORDER));
        } else if ("expiryDate".equals(sortField)) {
            documents.sort(Comparator.comparing(Document::getExpiryDate));
        }

        model.addAttribute("documents", documents);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        return "document_list";
    }*/

   /* @GetMapping("/document_list")
    public String showDocumentListPageNew(@RequestParam(value = "filter", required = false) String filter,
                                       Model model) {
        List<Document> documents;

        LocalDate today = LocalDate.now();

        if ("expired".equals(filter)) {
            documents = documentService.getExpiredDocuments(today);
        } else if ("expiringSoon".equals(filter)) {
            LocalDate nextWeek = today.plusDays(7);
            documents = documentService.getDocumentsExpiringWithin(today, nextWeek);
        } else {
            documents = documentService.getAllDocuments(); // default
        }

        model.addAttribute("documents", documents);
        model.addAttribute("filter", filter); // to retain filter on UI
        return "document_list"; // or your page name
    }*/

    @GetMapping("/document_list")
    public String showDocumentListPage(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sortField", required = false) String sortField,
            Model model) {

        List<Document> documents;

        // STEP 1: Search
        if (keyword != null && !keyword.isEmpty()) {
            documents = documentService.searchDocuments(keyword);
        } else {
            documents = documentService.getAllDocuments();
        }

        // STEP 2: Filter
        LocalDate today = LocalDate.now();
        if ("expired".equals(filter)) {
            documents = documents.stream()
                    .filter(doc -> doc.getExpiryDate() != null && doc.getExpiryDate().isBefore(today))
                    .toList();
        } else if ("expiringSoon".equals(filter)) {
            LocalDate nextWeek = today.plusDays(7);
            documents = documents.stream()
                    .filter(doc -> doc.getExpiryDate() != null &&
                            (doc.getExpiryDate().isAfter(today.minusDays(1)) && doc.getExpiryDate().isBefore(nextWeek.plusDays(1))))
                    .toList();
        }

        // STEP 3: Sort
        if ("documentName".equals(sortField)) {
            documents.sort(Comparator.comparing(Document::getDocumentName, String.CASE_INSENSITIVE_ORDER));
        } else if ("expiryDate".equals(sortField)) {
            documents.sort(Comparator.comparing(Document::getExpiryDate));
        }

        model.addAttribute("documents", documents);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", filter);
        model.addAttribute("sortField", sortField);

        return "document_list";
    }

    // ✅ 1. Show Add Document Form Page
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("document", new Document()); // binds empty form
        return "add_document"; // looks for add_document.html in templates
    }

    // ✅ 2. Handle Form Submission from Add Page
    @PostMapping("/save")
    public String saveDocument(@ModelAttribute Document document, RedirectAttributes redirectAttributes) {
        documentService.saveDocument(document); // Save to DB
        redirectAttributes.addFlashAttribute("message", "✅ Document Added successfully!");
        return "redirect:/documents/document_list"; // Redirect to the list page
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Document> allDocuments = documentService.getAllDocuments();

        int totalDocs = allDocuments.size();
        long expiringSoonCount = allDocuments.stream()
                .filter(doc -> doc.getExpiryDate() != null &&
                        doc.getExpiryDate().isBefore(LocalDate.now().plusDays(7)))
                .count();

        List<Document> recentDocs = allDocuments.stream()
                .sorted((d1, d2) -> d2.getId().compareTo(d1.getId()))
                .limit(5)
                .toList();

        model.addAttribute("totalDocs", totalDocs);
        model.addAttribute("expiringSoonCount", expiringSoonCount);
        model.addAttribute("recentDocs", recentDocs);

        return "dashboard";
    }
    // Load edit form
    @GetMapping("/edit_document/{id}")
    public String editDocument(@PathVariable Long id, Model model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("document", document);
        return "edit_document"; // Loads edit_document.html
    }

    // Handle form submission for update
    @PostMapping("/update")
    public String updateDocument(@ModelAttribute Document document, RedirectAttributes redirectAttributes) {
        documentService.saveDocument(document); // Reuse save for update
        redirectAttributes.addFlashAttribute("message", "✅ Document updated successfully!");
        return "redirect:/documents/document_list";
    }
    @GetMapping("/delete/{id}")
    public String deleteDocumentHtml(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        documentService.deleteDocument(id); // Delete from DB
        redirectAttributes.addFlashAttribute("message", "✅ Document deleted successfully!");
        return "redirect:/documents/document_list"; // Go back to the list page
    }
}