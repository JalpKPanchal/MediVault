package com.grownited;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.DoctorProfileService;
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
    public CommandLineRunner initData(UserService userService, DoctorProfileService doctorProfileService) {
        return args -> {
            // Create a test patient user
            UserEntity patient = new UserEntity();
            patient.setFirstName("John");
            patient.setLastName("Doe");
            patient.setEmail("patient@example.com");
            patient.setPassword("password123");
            patient.setRole(UserEntity.Role.PATIENT);
            userService.saveUser(patient);

            // Create a test doctor user
            UserEntity doctor = new UserEntity();
            doctor.setFirstName("Jane");
            doctor.setLastName("Smith");
            doctor.setEmail("doctor@example.com");
            doctor.setPassword("doctor123");
            doctor.setRole(UserEntity.Role.DOCTOR);
            userService.saveUser(doctor);

            // Create a doctor profile for the test doctor
            DoctorProfileEntity doctorProfile = new DoctorProfileEntity();
            doctorProfile.setUser(doctor);
            doctorProfile.setFirstName(doctor.getFirstName());
            doctorProfile.setLastName(doctor.getLastName());
            doctorProfileService.saveDoctorProfile(doctorProfile);

            // Create a test admin user
            UserEntity admin = new UserEntity();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123");
            admin.setRole(UserEntity.Role.ADMIN);
            userService.saveUser(admin);

            System.out.println("Test patient user created: patient@example.com / password123");
            System.out.println("Test doctor user created: doctor@example.com / doctor123");
            System.out.println("Test admin user created: admin@example.com / admin123");
        };
    }
}