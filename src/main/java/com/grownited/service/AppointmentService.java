package com.grownited.service;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.repository.AppointmentRepository;
import com.grownited.repository.DoctorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

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

    // ✅ Get all appointments for a patient
    public List<AppointmentEntity> getAppointmentsByPatientId(UUID patientId) {
        return repository.findByPatientId(patientId);
    }

    // ✅ Get all appointments for a doctor
    public List<AppointmentEntity> getAppointmentsByDoctorId(Integer doctorId) {
        Optional<DoctorProfileEntity> doctor = doctorProfileRepository.findById(doctorId);

        return doctor.map(repository::findByDoctor).orElse(List.of());
    }
}
