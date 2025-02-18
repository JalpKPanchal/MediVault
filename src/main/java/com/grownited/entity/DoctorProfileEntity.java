package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor_profile") // Ensure matches database
public class DoctorProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_profile_id") // Matches database column name
    private Integer docProfileId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private Integer experience;

    @Column(name = "profile_pic", nullable = false) 
    private String profilePic;

    @Column
    private String about;

    @Column
    private String registrationNo;
}
