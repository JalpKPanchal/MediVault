package com.grownited.controller;

import com.grownited.entity.CityEntity;
import com.grownited.entity.ClinicEntity;
import com.grownited.entity.StateEntity;
import com.grownited.service.CityService;
import com.grownited.service.ClinicService;
import com.grownited.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public String listClinics(Model model) {
        List<ClinicEntity> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        return "ClinicList";
    }

    @GetMapping("/new")
    public String newClinicForm(Model model) {
        List<StateEntity> states = stateService.getAllStates();
        List<CityEntity> cities = cityService.getAllCities();

        model.addAttribute("clinicEntity", new ClinicEntity());
        model.addAttribute("states", states);
        model.addAttribute("cities", cities);

        return "ClinicForm";
    }

    @PostMapping("/save")
    public String saveClinic(@ModelAttribute ClinicEntity clinicEntity) {
        clinicService.saveClinic(clinicEntity);
        return "redirect:/clinic/list";
    }

    @GetMapping("/edit/{clinicId}")
    public String editClinic(@PathVariable Long clinicId, Model model) { // Changed Integer to Long
        Optional<ClinicEntity> clinic = clinicService.getClinicById(clinicId);
        if (clinic.isPresent()) {
            List<StateEntity> states = stateService.getAllStates();
            List<CityEntity> cities = cityService.getAllCities();

            model.addAttribute("clinicEntity", clinic.get());
            model.addAttribute("states", states);
            model.addAttribute("cities", cities);

            return "ClinicForm";
        }
        return "redirect:/clinic/list";
    }

    @GetMapping("/delete/{clinicId}")
    public String deleteClinic(@PathVariable Long clinicId) { // Changed Integer to Long
        clinicService.deleteClinic(clinicId);
        return "redirect:/clinic/list";
    }
}