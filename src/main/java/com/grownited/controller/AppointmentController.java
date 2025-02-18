package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;

import com.grownited.service.AppointmentService;
import com.grownited.service.DoctorProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorProfileService doctorProfileService;  // Service for fetching doctor profiles

    // Display the appointment list
    @GetMapping
    public String listAppointments(Model model) {
        List<AppointmentEntity> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "AppointmentList";
    }

    // Show the form to create/edit an appointment
    @GetMapping("/new")
    public String showNewForm(Model model) {
        List<DoctorProfileEntity> doctors = doctorProfileService.getAllDoctors();  // Get all doctors
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", new AppointmentEntity());
        return "AppointmentForm";
    }

    // Save the appointment (create or update)
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute AppointmentEntity appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    // Edit an appointment
    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable UUID id, Model model) {
        AppointmentEntity appointment = appointmentService.getAppointmentById(id);
        List<DoctorProfileEntity> doctors = doctorProfileService.getAllDoctors();  // Get all doctors
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", appointment);
        return "AppointmentForm";
    }

    // Delete an appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable UUID id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
