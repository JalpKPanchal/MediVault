package com.grownited.controller;

import com.grownited.entity.PatientMedicalHistoryEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.PatientMedicalHistoryService;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicalHistory")
public class PatientMedicalHistoryController {

    @Autowired
    private PatientMedicalHistoryService medicalHistoryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listMedicalHistory(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }

        List<PatientMedicalHistoryEntity> history = medicalHistoryService.getMedicalHistoryByPatientId(loggedInUser.getUserId());
        model.addAttribute("history", history);
        return "MedicalHistoryList";
    }

    @GetMapping("/new")
    public String showNewForm(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/user/login?error=Unauthorized";
        }

        model.addAttribute("history", new PatientMedicalHistoryEntity());
        model.addAttribute("patients", userService.getAllDoctors());
        return "MedicalHistoryForm";
    }

    @PostMapping("/save")
    public String saveMedicalHistory(@ModelAttribute PatientMedicalHistoryEntity history) {
        medicalHistoryService.saveMedicalHistory(history);
        return "redirect:/medicalHistory";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistory(id);
        return "redirect:/medicalHistory";
    }
}