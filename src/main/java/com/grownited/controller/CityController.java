package com.grownited.controller;

import com.grownited.entity.CityEntity;
import com.grownited.entity.StateEntity;
import com.grownited.service.CityService;
import com.grownited.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    // Show all cities
    @GetMapping("/list")
    public String listCities(Model model) {
        List<CityEntity> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "CityList";  // Updated to use CityList.jsp
    }

    // Show form to add a new city
    @GetMapping("/new")
    public String newCityForm(Model model) {
        List<StateEntity> states = stateService.getAllStates();  // Fetch all states for the dropdown
        model.addAttribute("states", states);
        model.addAttribute("cityEntity", new CityEntity());
        return "CityForm";  // Updated to use CityForm.jsp
    }

    // Create or update city
    @PostMapping("/save")
    public String saveCity(@ModelAttribute CityEntity cityEntity) {
        cityService.saveCity(cityEntity);
        return "redirect:/city/list";
    }

    // Show form to update a city
    @GetMapping("/edit/{cityId}")
    public String editCity(@PathVariable Integer cityId, Model model) {
        Optional<CityEntity> city = cityService.getCityById(cityId);
        if (city.isPresent()) {
            List<StateEntity> states = stateService.getAllStates();  // Fetch all states for the dropdown
            model.addAttribute("states", states);
            model.addAttribute("cityEntity", city.get());
            return "CityForm";  // Updated to use CityForm.jsp
        }
        return "redirect:/city/list";
    }

    // Delete city
    @GetMapping("/delete/{cityId}")
    public String deleteCity(@PathVariable Integer cityId) {
        cityService.deleteCity(cityId);
        return "redirect:/city/list";
    }
}
