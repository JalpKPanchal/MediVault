package com.grownited.service;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.AppointmentEntity.AppointmentStatus;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    // Fetch appointments for a specific patient
    public List<AppointmentEntity> getAppointmentsByPatientId(UUID patientId) {
        return repository.findByPatientId(patientId);
    }

    // Fetch appointments for a specific doctor
    public List<AppointmentEntity> getAppointmentsByDoctor(DoctorProfileEntity doctor) {
        return repository.findByDoctor(doctor);
    }

    // Fetch appointments by status
    public List<AppointmentEntity> getAppointmentsByStatus(AppointmentStatus status) {
        return repository.findByStatus(status);
    }

    // Fetch appointments by doctor and status
    public List<AppointmentEntity> getAppointmentsByDoctorAndStatus(DoctorProfileEntity doctor, AppointmentStatus status) {
        return repository.findByDoctorAndStatus(doctor, status);
    }

    // Save or update an appointment
    public AppointmentEntity saveAppointment(AppointmentEntity appointment) {
        return repository.save(appointment);
    }

    // Fetch an appointment by ID
    public AppointmentEntity getAppointmentById(UUID appointmentId) {
        return repository.findById(appointmentId).orElse(null);
    }

    // Delete an appointment
    public void deleteAppointment(UUID appointmentId) {
        repository.deleteById(appointmentId);
    }
}