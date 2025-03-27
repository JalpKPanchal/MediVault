package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.AppointmentService;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class DashboardController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    public DashboardController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/user/login?error=Please login to access the dashboard";
        }

        model.addAttribute("user", loggedInUser);

        if (loggedInUser.getRole() == UserEntity.Role.PATIENT) {
            List<AppointmentEntity> appointments = appointmentService.getAppointmentsByPatientId(loggedInUser.getUserId());
            model.addAttribute("appointments", appointments);
            return "PatientDashboard";
        } else if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
            List<AppointmentEntity> appointments = appointmentService.getAppointmentsByDoctorId(loggedInUser.getUserId());
            Map<Long, String> patientNames = new HashMap<>();
            for (AppointmentEntity appointment : appointments) {
                Optional<UserEntity> patient = userService.getUserById(appointment.getPatientId());
                patientNames.put(appointment.getPatientId(), patient.map(user -> user.getFirstName() + " " + user.getLastName()).orElse("Unknown"));
            }
            model.addAttribute("appointments", appointments);
            model.addAttribute("patientNames", patientNames);
            return "DoctorDashboard";
        } else if (loggedInUser.getRole() == UserEntity.Role.ADMIN) {
            return "AdminDashboard";
        } else {
            return "redirect:/user/login?error=Invalid role";
        }
    }
}