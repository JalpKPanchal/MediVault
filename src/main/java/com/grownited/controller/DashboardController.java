package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.AppointmentService;
import com.grownited.service.DoctorProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorProfileService doctorProfileService;

    @GetMapping
    public String showDashboard(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/user/login"; // Redirect to login page if not logged in
        }

        if (loggedInUser.getRole() == UserEntity.Role.PATIENT) {
            List<AppointmentEntity> appointments = appointmentService.getAppointmentsByPatientId(loggedInUser.getUserId());
            model.addAttribute("appointments", appointments);
            model.addAttribute("user", loggedInUser);
            return "PatientDashboard";
        }

        if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
            Optional<DoctorProfileEntity> doctorProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());

            if (doctorProfile.isPresent()) {
                Integer doctorId = doctorProfile.get().getDocProfileId();
                List<AppointmentEntity> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
                model.addAttribute("appointments", appointments);
            } else {
                model.addAttribute("error", "Doctor profile not found.");
            }

            model.addAttribute("user", loggedInUser);
            return "DoctorDashboard";
        }

        model.addAttribute("user", loggedInUser);
        return "AdminDashboard";
    }
}
