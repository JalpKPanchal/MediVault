package com.grownited.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DoctorProfile")
public class DoctorProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docProfileId")
    private Integer docProfileId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

    @Column(name = "qualification", nullable = false)
    private String qualification;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Column(name = "profile_pic", nullable = false)
    private String profilePic;

    @Column(name = "about")
    private String about;

    @Column(name = "registrationno")
    private String registrationNo;
}
