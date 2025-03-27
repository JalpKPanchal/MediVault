package com.grownited.controller;

import com.grownited.entity.UserEntity;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientMedicalHistoryController {

    private final UserService userService;

    public PatientMedicalHistoryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/patient/medicalHistory")
    public String viewMedicalHistory(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.PATIENT) {
            return "redirect:/user/login?error=Please login as a patient to view medical history";
        }

        model.addAttribute("user", loggedInUser);
        return "MedicalHistory";
    }
}