package com.grownited.service;

import com.grownited.entity.AppointmentEntity;
import com.grownited.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<AppointmentEntity> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<AppointmentEntity> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorUserId(doctorId);
    }

    public void saveAppointment(AppointmentEntity appointment) {
        appointmentRepository.save(appointment);
    }
}