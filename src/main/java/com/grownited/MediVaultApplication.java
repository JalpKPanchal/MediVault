package com.grownited;

import com.grownited.entity.UserEntity;
import com.grownited.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediVaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediVaultApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserService userService) {
        return args -> {
            // Create a test patient
            UserEntity patient = new UserEntity();
            patient.setFirstName("Patient");
            patient.setLastName("User");
            patient.setEmail("patient@example.com");
            patient.setPassword("password123");
            patient.setBornYear(1990); // Set a value for bornYear
            patient.setRole(UserEntity.Role.PATIENT);
            userService.saveUser(patient);

            // Create a test doctor
            UserEntity doctor = new UserEntity();
            doctor.setFirstName("Doctor");
            doctor.setLastName("User");
            doctor.setEmail("doctor@example.com");
            doctor.setPassword("doctor123");
            doctor.setBornYear(1985); // Set a value for bornYear
            doctor.setRole(UserEntity.Role.DOCTOR);
            userService.saveUser(doctor);

            // Create a test admin
            UserEntity admin = new UserEntity();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123");
            admin.setBornYear(1980); // Set a value for bornYear
            admin.setRole(UserEntity.Role.ADMIN);
            userService.saveUser(admin);
            
          
        };
    }
}