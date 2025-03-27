package com.grownited.service;

import com.grownited.entity.UserEntity;
import com.grownited.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity authenticate(String email, String password) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (user.getPassword().equals(password)) { // In production, use password hashing
                return user;
            }
        }
        return null;
    }

    public Optional<UserEntity> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    // New method to fetch all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}