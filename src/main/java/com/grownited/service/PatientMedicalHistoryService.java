package com.grownited.service;

import com.grownited.entity.PatientMedicalHistoryEntity;
import com.grownited.repository.PatientMedicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientMedicalHistoryService {

    private final PatientMedicalHistoryRepository repository;

    public PatientMedicalHistoryService(PatientMedicalHistoryRepository repository) {
        this.repository = repository;
    }

    public List<PatientMedicalHistoryEntity> getMedicalHistoryByPatientId(Long patientId) {
        return repository.findByPatient_UserId(patientId);
    }

    public PatientMedicalHistoryEntity save(PatientMedicalHistoryEntity history) {
        return repository.save(history);
    }
}