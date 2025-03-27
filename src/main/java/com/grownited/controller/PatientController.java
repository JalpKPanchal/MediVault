package com.grownited.controller;

import com.grownited.entity.PatientMedicalHistoryEntity;
import com.grownited.service.PatientMedicalHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientMedicalHistoryService medicalHistoryService;

    public PatientController(PatientMedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping("/medicalHistory/{patientId}")
    public String viewMedicalHistory(@PathVariable Long patientId, Model model) {
        List<PatientMedicalHistoryEntity> history = medicalHistoryService.getMedicalHistoryByPatientId(patientId);
        model.addAttribute("medicalHistory", history);
        model.addAttribute("patientId", patientId);
        return "PatientMedicalHistory";
    }
}