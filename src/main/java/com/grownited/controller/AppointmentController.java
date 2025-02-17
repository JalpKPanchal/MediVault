package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.service.AppointmentService;
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
    private AppointmentService service;

    @GetMapping
    public String listAppointments(Model model) {
        List<AppointmentEntity> appointments = service.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "AppointmentList";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("appointment", new AppointmentEntity());
        return "AppointmentForm";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute AppointmentEntity appointment) {
        service.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable UUID id, Model model) {
        AppointmentEntity appointment = service.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "AppointmentForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable UUID id) {
        service.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
