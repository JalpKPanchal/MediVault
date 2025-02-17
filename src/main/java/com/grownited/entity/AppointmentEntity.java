package com.grownited.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @Column(nullable = false)
    private UUID patientId;

    @Column(nullable = false)
    private UUID doctorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Temporal(TemporalType.DATE)
    private Date appCreateDate;

    private String comment;

    private UUID clinicId;

    private String reference;

    private String complain;

    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    private String appointmentTime;

    private String cancelReason;

    public enum AppointmentStatus {
        CANCELLED, BOOKED, REJECTED, RESCHEDULED, PENDING
    }

}
