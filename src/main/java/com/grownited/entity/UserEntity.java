package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password; // Encrypted

    private String gender;
    private String contactNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int bornYear;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientMedicalHistoryEntity> medicalHistory;

    public enum Role {
        ADMIN, PATIENT, DOCTOR, FDE
    }

    public enum Status {
        ACTIVE, DISABLED
    }

    public String getRoleAsString() {
        return role.name();
    }
}