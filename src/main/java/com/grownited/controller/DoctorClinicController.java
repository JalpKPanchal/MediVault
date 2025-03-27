package com.grownited.controller;

import com.grownited.entity.ClinicEntity;
import com.grownited.entity.DoctorClinicEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.ClinicService;
import com.grownited.service.DoctorClinicService;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctorClinic")
public class DoctorClinicController {

    private final UserService userService;
    private final ClinicService clinicService;
    private final DoctorClinicService doctorClinicService;

    public DoctorClinicController(UserService userService, ClinicService clinicService, DoctorClinicService doctorClinicService) {
        this.userService = userService;
        this.clinicService = clinicService;
        this.doctorClinicService = doctorClinicService;
    }

    @GetMapping("/list")
    public String listClinics(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        List<ClinicEntity> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        return "ListClinics";
    }

    @GetMapping("/form")
    public String clinicForm(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        model.addAttribute("clinic", new ClinicEntity());
        return "ClinicForm";
    }

    @PostMapping("/saveClinic")
    public String saveClinic(
            @RequestParam("clinicId") Long clinicId,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        ClinicEntity clinic;
        if (clinicId != null && clinicId > 0) {
            Optional<ClinicEntity> existingClinic = clinicService.getClinicById(clinicId);
            if (existingClinic.isEmpty()) {
                return "redirect:/doctorClinic/list?error=Clinic not found";
            }
            clinic = existingClinic.get();
        } else {
            clinic = new ClinicEntity();
        }

        clinic.setName(name);
        clinic.setAddress(address);
        clinicService.saveClinic(clinic);
        return "redirect:/doctorClinic/list?success=Clinic saved successfully";
    }

    @GetMapping("/assign/{clinicId}")
    public String assignDoctorForm(@PathVariable("clinicId") Long clinicId, Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        Optional<ClinicEntity> clinic = clinicService.getClinicById(clinicId);
        if (clinic.isEmpty()) {
            return "redirect:/doctorClinic/list?error=Clinic not found";
        }

        // Fetch all doctors using userService.getAllUsers()
        List<UserEntity> doctors = userService.getAllUsers()
                .stream()
                .filter(user -> user.getRole() == UserEntity.Role.DOCTOR)
                .collect(Collectors.toList());

        List<DoctorClinicEntity> existingAssignments = doctorClinicService.getDoctorsByClinicId(clinicId);

        model.addAttribute("clinic", clinic.get());
        model.addAttribute("doctors", doctors);
        model.addAttribute("existingAssignments", existingAssignments);
        return "AssignDoctorToClinic";
    }

    @PostMapping("/assign")
    public String assignDoctor(
            @RequestParam("clinicId") Long clinicId,
            @RequestParam("doctorId") Long doctorId,
            HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        Optional<ClinicEntity> clinic = clinicService.getClinicById(clinicId);
        Optional<UserEntity> doctor = userService.getUserById(doctorId);

        if (clinic.isEmpty() || doctor.isEmpty() || doctor.get().getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/doctorClinic/list?error=Invalid clinic or doctor";
        }

        DoctorClinicEntity doctorClinic = new DoctorClinicEntity();
        doctorClinic.setClinic(clinic.get());
        doctorClinic.setDoctor(doctor.get());
        doctorClinicService.saveDoctorClinic(doctorClinic);

        return "redirect:/doctorClinic/assign/" + clinicId + "?success=Doctor assigned successfully";
    }

    @GetMapping("/deleteAssignment/{id}")
    public String deleteAssignment(@PathVariable("id") Long id, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        DoctorClinicEntity assignment = doctorClinicService.getDoctorsByClinicId(id).stream()
                .filter(dc -> dc.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (assignment == null) {
            return "redirect:/doctorClinic/list?error=Assignment not found";
        }

        doctorClinicService.deleteDoctorClinic(id);
        return "redirect:/doctorClinic/assign/" + assignment.getClinic().getClinicId() + "?success=Assignment deleted successfully";
    }

    @GetMapping("/deleteClinic/{clinicId}")
    public String deleteClinic(@PathVariable("clinicId") Long clinicId, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        clinicService.deleteClinic(clinicId);
        return "redirect:/doctorClinic/list?success=Clinic deleted successfully";
    }
}