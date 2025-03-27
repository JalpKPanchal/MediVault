package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.AppointmentService;
import com.grownited.service.DoctorProfileService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorProfileService doctorProfileService;

    @GetMapping
    public String showDashboard(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            logger.warn("User not logged in, redirecting to login page.");
            return "redirect:/user/login";
        }

        logger.info("User {} (Role: {}) accessed the dashboard.", loggedInUser.getEmail(), loggedInUser.getRole());

        if (loggedInUser.getRole() == UserEntity.Role.PATIENT) {
            List<AppointmentEntity> appointments = appointmentService.getAppointmentsByPatientId(loggedInUser.getUserId());
            model.addAttribute("appointments", appointments);
            model.addAttribute("user", loggedInUser);
            logger.debug("Found {} appointments for patient with ID: {}", appointments.size(), loggedInUser.getUserId());
            return "PatientDashboard";
        }

        if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
            DoctorProfileEntity doctorProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId()).orElse(null);

            if (doctorProfile != null) {
                List<AppointmentEntity> appointments = appointmentService.getAppointmentsByDoctor(doctorProfile);
                model.addAttribute("appointments", appointments);
                logger.debug("Found {} appointments for doctor with ID: {}", appointments.size(), doctorProfile.getDocProfileId());
            } else {
                model.addAttribute("error", "Doctor profile not found. Please create your profile.");
                model.addAttribute("appointments", List.of());
                logger.warn("Doctor profile not found for user ID: {}", loggedInUser.getUserId());
            }

            model.addAttribute("statusList", Arrays.asList(AppointmentEntity.AppointmentStatus.values()));
            model.addAttribute("user", loggedInUser);
            return "DoctorDashboard";
        }

        model.addAttribute("user", loggedInUser);
        return "AdminDashboard";
    }
}