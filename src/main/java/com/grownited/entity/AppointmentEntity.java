package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @Column(nullable = false)
    private UUID patientId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doc_profile_id") // Matches DoctorProfileEntity
    private DoctorProfileEntity doctor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Temporal(TemporalType.DATE)
    private Date appCreateDate;

    private String comment;

    private UUID clinicId;

    private String reference;

    private String complain;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    private String appointmentTime;

    private String cancelReason;

    public enum AppointmentStatus {
        CANCELLED, BOOKED, REJECTED, RESCHEDULED, PENDING
    }
}
