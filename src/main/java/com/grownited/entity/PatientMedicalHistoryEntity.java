package com.grownited.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patient_medical_history")
public class PatientMedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    private UserEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    private UserEntity doctor;

    private String diagnosis;
    private String treatment;
    private String prescription;

    // Getters and Setters
    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public UserEntity getPatient() {
        return patient;
    }

    public void setPatient(UserEntity patient) {
        this.patient = patient;
    }

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}