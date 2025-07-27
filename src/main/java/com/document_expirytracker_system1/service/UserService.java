package com.document_expirytracker_system1.service;

import com.document_expirytracker_system1.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    void saveUser(User user); // For registration (optional)

}
