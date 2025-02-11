package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
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

    public enum Role {
        ADMIN, PATIENT, DOCTOR, FDE
    }

    public enum Status {
        ACTIVE, DISABLED
    }

    // New method to return role as String for JSP
    public String getRoleAsString() {
        return role.name();
    }
}
