package com.grownited.service;

import com.grownited.entity.UserEntity;
import com.grownited.entity.UserEntity.Role;
import com.grownited.entity.UserEntity.Status;
import com.grownited.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.PATIENT);
        return userRepository.save(user);
    }

    public Optional<UserEntity> login(String email, String password) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            if (user.getStatus() == Status.DISABLED) {
                return Optional.empty();
            }
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }
    public List<UserEntity> getAllDoctors() {
        return userRepository.findByRole(Role.DOCTOR);
    }
    public Optional<UserEntity> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }


}
