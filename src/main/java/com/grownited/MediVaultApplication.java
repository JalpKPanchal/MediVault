package com.grownited;

import com.grownited.entity.UserEntity;
import com.grownited.entity.UserEntity.Role;
import com.grownited.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class MediVaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediVaultApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create test patient user
            UserEntity patient = new UserEntity();
            patient.setUserId(UUID.randomUUID());
            patient.setEmail("patient@example.com");
            patient.setFirstName("Test");
            patient.setLastName("Patient");
            patient.setPassword(passwordEncoder.encode("password123"));
            patient.setRole(Role.PATIENT);
            userService.saveUser(patient);
            System.out.println("Test patient user created: patient@example.com / password123");

            // Create test doctor user
            UserEntity doctor = new UserEntity(); // Use UserEntity instead of User
            doctor.setUserId(UUID.randomUUID());
            doctor.setEmail("doctor@example.com");
            doctor.setFirstName("Test");
            doctor.setLastName("Doctor");
            doctor.setPassword(passwordEncoder.encode("doctor123")); // Fix: Set password on doctor, not patient
            doctor.setRole(Role.DOCTOR);
            userService.saveUser(doctor);
            System.out.println("Test doctor user created: doctor@example.com / doctor123");

            // Create test admin user
            UserEntity admin = new UserEntity(); // Use UserEntity instead of User
            admin.setUserId(UUID.randomUUID());
            admin.setEmail("admin@example.com");
            admin.setFirstName("Test");
            admin.setLastName("Admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userService.saveUser(admin);
            System.out.println("Test admin user created: admin@example.com / admin123");
        };
    }
}