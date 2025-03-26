package com.grownited.service;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.repository.AppointmentRepository;
import com.grownited.repository.DoctorProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    public AppointmentEntity saveAppointment(AppointmentEntity appointmentEntity) {
        return repository.save(appointmentEntity);
    }

    public AppointmentEntity getAppointmentById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<AppointmentEntity> getAllAppointments() {
        return repository.findAll();
    }

    public void deleteAppointment(UUID id) {
        repository.deleteById(id);
    }

    public List<AppointmentEntity> getAppointmentsByPatientId(UUID patientId) {
        return repository.findByPatientId(patientId);
    }

    public List<AppointmentEntity> getAppointmentsByDoctorId(Integer doctorId) {
        Optional<DoctorProfileEntity> doctor = doctorProfileRepository.findById(doctorId);
        return doctor.map(repository::findByDoctor).orElse(List.of());
    }

    public List<AppointmentEntity> getAppointmentsByStatus(String status) {
        try {
            AppointmentEntity.AppointmentStatus appointmentStatus = AppointmentEntity.AppointmentStatus.valueOf(status.toUpperCase());
            return repository.findByStatus(appointmentStatus);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid status value: {}", status, e);
            return List.of();
        }
    }

    // New method to get appointments by doctor and status
    public List<AppointmentEntity> getAppointmentsByDoctorIdAndStatus(Integer doctorId, String status) {
        try {
            AppointmentEntity.AppointmentStatus appointmentStatus = AppointmentEntity.AppointmentStatus.valueOf(status.toUpperCase());
            Optional<DoctorProfileEntity> doctor = doctorProfileRepository.findById(doctorId);
            if (doctor.isPresent()) {
                return repository.findByDoctorAndStatus(doctor.get(), appointmentStatus);
            }
            return List.of();
        } catch (IllegalArgumentException e) {
            logger.error("Invalid status value: {}", status, e);
            return List.of();
        }
    }
}