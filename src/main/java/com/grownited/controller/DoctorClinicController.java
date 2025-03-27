package com.grownited.controller;

import com.grownited.entity.DoctorClinicEntity;
import com.grownited.service.DoctorClinicService;
import com.grownited.service.ClinicService;
import com.grownited.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctorClinic")
public class DoctorClinicController {

    private final DoctorClinicService doctorClinicService;
    private final UserService userService;
    private final ClinicService clinicService;

    public DoctorClinicController(DoctorClinicService doctorClinicService, UserService userService, ClinicService clinicService) {
        this.doctorClinicService = doctorClinicService;
        this.userService = userService;
        this.clinicService = clinicService;
    }

    @GetMapping("/list")
    public String listDoctorClinics(Model model) {
        List<DoctorClinicEntity> doctorClinics = doctorClinicService.getAllDoctorClinics();
        model.addAttribute("doctorClinics", doctorClinics);
        return "DoctorClinicList";
    }

    @GetMapping("/new")
    public String newDoctorClinicForm(Model model) {
        model.addAttribute("doctorClinicEntity", new DoctorClinicEntity());
        model.addAttribute("users", userService.getAllDoctors()); // ✅ This will work now
        model.addAttribute("clinics", clinicService.getAllClinics());
        return "DoctorClinicForm";
    }

    @PostMapping("/save")
    public String saveDoctorClinic(@ModelAttribute DoctorClinicEntity doctorClinicEntity) {
        doctorClinicService.saveDoctorClinic(doctorClinicEntity);
        return "redirect:/doctorClinic/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctorClinic(@PathVariable Integer id) {
        doctorClinicService.deleteDoctorClinic(id);
        return "redirect:/doctorClinic/list";
    }
}
