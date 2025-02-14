package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doct_clinic")
public class DoctorClinicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Composite keys are possible, but this is simpler

    @ManyToOne
    @JoinColumn(name = "docid", nullable = false)
    private UserEntity user; // Assuming Doctor is represented by UserEntity

    @ManyToOne
    @JoinColumn(name = "clinicid", nullable = false)
    private ClinicEntity clinic;
}