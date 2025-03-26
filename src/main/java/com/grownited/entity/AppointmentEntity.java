package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfileEntity doctor;

    @Column(name = "patient_id")
    private UUID patientId;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String comment;

    public enum AppointmentStatus {
        PENDING, BOOKED, CANCELLED, REJECTED, RESCHEDULED
    }

    // Formatted getters for JSP
    public String getFormattedAppointmentDate() {
        return appointmentDate != null ? appointmentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "";
    }

    public String getFormattedAppointmentTime() {
        return appointmentTime != null ? appointmentTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
    }
}