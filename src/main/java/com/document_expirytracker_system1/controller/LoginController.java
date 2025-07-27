package com.document_expirytracker_system1.controller;

import com.document_expirytracker_system1.model.User;
import com.document_expirytracker_system1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        // Show login page
        @GetMapping("/login")
        public String showLoginPage() {
            return "login"; // Already existing
        }

        // Show registration page
        @GetMapping("/register")
        public String showRegisterForm() {
            return "register";
        }

        // Handle registration form submission
        @PostMapping("/register")
        public String processRegister(@RequestParam String username,
                                      @RequestParam String password,
                                      RedirectAttributes redirectAttributes) {

            if (userRepository.findByUsername(username).isPresent()) {
                redirectAttributes.addFlashAttribute("message", "❌ Username already exists!");
                return "redirect:/register";
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password)); // Encrypt password
            user.setRole("ROLE_USER"); // Or "ROLE_ADMIN" if needed

            userRepository.save(user);

            redirectAttributes.addFlashAttribute("message", "✅ Registration successful! Please login.");
            return "redirect:/login";
        }

}
