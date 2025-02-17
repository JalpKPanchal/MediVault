package com.grownited.service;

import com.grownited.entity.AppointmentEntity;
import com.grownited.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

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
}
