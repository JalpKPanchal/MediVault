package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "patient_medical_history")
public class PatientMedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfileEntity doctor;

    private String diagnosis;
    private String treatment;
    private LocalDate visitDate;
    private String notes;
}