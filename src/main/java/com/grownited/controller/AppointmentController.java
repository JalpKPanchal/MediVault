package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.AppointmentService;
import com.grownited.service.DoctorProfileService;
import com.grownited.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorProfileService doctorProfileService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String listAppointments(Model model, @RequestParam(required = false) String status, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            logger.warn("User not logged in, redirecting to login page.");
            return "redirect:/user/login";
        }

        if (loggedInUser.getRole() != UserEntity.Role.ADMIN && 
            loggedInUser.getRole() != UserEntity.Role.DOCTOR && 
            loggedInUser.getRole() != UserEntity.Role.FDE) {
            logger.warn("User {} (Role: {}) does not have permission to view appointments.", loggedInUser.getEmail(), loggedInUser.getRole());
            model.addAttribute("error", "You do not have permission to view appointments.");
            return "Dashboard";
        }

        List<AppointmentEntity> appointments;
        try {
            if (status != null && !status.isEmpty()) {
                AppointmentEntity.AppointmentStatus appointmentStatus = AppointmentEntity.AppointmentStatus.valueOf(status.toUpperCase());
                if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
                    DoctorProfileEntity doctorProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());
                    if (doctorProfile != null) {
                        appointments = appointmentService.getAppointmentsByDoctorIdAndStatus(doctorProfile.getDocProfileId(), status.toUpperCase());
                        logger.debug("Found {} appointments for doctor ID {} with status {}", appointments.size(), doctorProfile.getDocProfileId(), status);
                    } else {
                        appointments = List.of();
                        logger.warn("Doctor profile not found for user ID: {}", loggedInUser.getUserId());
                    }
                } else {
                    appointments = appointmentService.getAppointmentsByStatus(status.toUpperCase());
                    logger.debug("Found {} appointments with status {}", appointments.size(), status);
                }
            } else {
                if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
                    DoctorProfileEntity doctorProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());
                    if (doctorProfile != null) {
                        appointments = appointmentService.getAppointmentsByDoctorId(doctorProfile.getDocProfileId());
                        logger.debug("Found {} appointments for doctor ID {}", appointments.size(), doctorProfile.getDocProfileId());
                    } else {
                        appointments = List.of();
                        logger.warn("Doctor profile not found for user ID: {}", loggedInUser.getUserId());
                    }
                } else {
                    appointments = appointmentService.getAllAppointments();
                    logger.debug("Found {} total appointments for user with role {}", appointments.size(), loggedInUser.getRole());
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid status value: {}", status, e);
            model.addAttribute("error", "Invalid status value: " + status);
            if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
                DoctorProfileEntity doctorProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());
                if (doctorProfile != null) {
                    appointments = appointmentService.getAppointmentsByDoctorId(doctorProfile.getDocProfileId());
                    logger.debug("Found {} appointments for doctor ID {} after invalid status", appointments.size(), doctorProfile.getDocProfileId());
                } else {
                    appointments = List.of();
                    logger.warn("Doctor profile not found for user ID: {}", loggedInUser.getUserId());
                }
            } else {
                appointments = appointmentService.getAllAppointments();
                logger.debug("Found {} total appointments after invalid status", appointments.size());
            }
        }

        // Add the list of AppointmentStatus values to the model for the dropdowns
        model.addAttribute("statusList", Arrays.asList(AppointmentEntity.AppointmentStatus.values()));
        model.addAttribute("appointments", appointments);
        return "AppointmentList";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        List<DoctorProfileEntity> doctors = doctorProfileService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", new AppointmentEntity());
        return "AppointmentForm";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute AppointmentEntity appointment, Model model) {
        try {
            appointment.setStatus(AppointmentEntity.AppointmentStatus.PENDING);
            appointmentService.saveAppointment(appointment);
            emailService.sendAppointmentConfirmation(appointment);
            logger.info("Appointment saved successfully: {}", appointment.getAppointmentId());
        } catch (Exception e) {
            logger.error("Error saving appointment: {}", e.getMessage());
            model.addAttribute("error", "Failed to save appointment. Please try again.");
            return "AppointmentForm";
        }
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable UUID id, Model model) {
        AppointmentEntity appointment = appointmentService.getAppointmentById(id);
        List<DoctorProfileEntity> doctors = doctorProfileService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", appointment);
        return "AppointmentForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable UUID id) {
        appointmentService.deleteAppointment(id);
        logger.info("Appointment deleted: {}", id);
        return "redirect:/appointments";
    }

    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable UUID id, @RequestParam String status) {
        try {
            AppointmentEntity appointment = appointmentService.getAppointmentById(id);
            if (appointment != null) {
                appointment.setStatus(AppointmentEntity.AppointmentStatus.valueOf(status.toUpperCase()));
                appointmentService.saveAppointment(appointment);
                emailService.sendAppointmentStatusUpdate(appointment);
                logger.info("Appointment status updated: {} to {}", id, status);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid status value for update: {}", status, e);
        }
        return "redirect:/appointments";
    }
}