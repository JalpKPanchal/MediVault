package com.grownited.service;

import com.grownited.entity.UserEntity;
import com.grownited.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Authenticate a user by email and password
    public UserEntity authenticate(String email, String password) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    // Fetch a user by ID
    public Optional<UserEntity> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    // Save or update a user
    public UserEntity saveUser(UserEntity user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    // Fetch all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Fetch all doctors (users with role DOCTOR)
    public List<UserEntity> getAllDoctors() {
        return userRepository.findByRole(UserEntity.Role.DOCTOR);
    }
}