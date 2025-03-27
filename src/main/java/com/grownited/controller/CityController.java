package com.grownited.controller;

import com.grownited.entity.CityEntity;
import com.grownited.entity.StateEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.CityService;
import com.grownited.service.StateService;
import jakarta.servlet.http.HttpSession;
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
    public String listCities(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        List<CityEntity> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "CityList";  // Using Thymeleaf template (CityList.html)
    }

    // Show form to add a new city
    @GetMapping("/new")
    public String newCityForm(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        List<StateEntity> states = stateService.getAllStates();
        model.addAttribute("states", states);
        model.addAttribute("cityEntity", new CityEntity());
        return "CityForm";  // Using Thymeleaf template (CityForm.html)
    }

    // Create or update city
    @PostMapping("/save")
    public String saveCity(@ModelAttribute CityEntity cityEntity, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        cityService.saveCity(cityEntity);
        return "redirect:/city/list?success=City saved successfully";
    }

    // Show form to update a city
    @GetMapping("/edit/{cityId}")
    public String editCity(@PathVariable Long cityId, Model model, HttpSession session) { // Changed Integer to Long
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        Optional<CityEntity> city = cityService.getCityById(cityId);
        if (city.isPresent()) {
            List<StateEntity> states = stateService.getAllStates();
            model.addAttribute("states", states);
            model.addAttribute("cityEntity", city.get());
            return "CityForm";
        }
        return "redirect:/city/list?error=City not found";
    }

    // Delete city
    @GetMapping("/delete/{cityId}")
    public String deleteCity(@PathVariable Long cityId, HttpSession session) { // Changed Integer to Long
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        cityService.deleteCity(cityId);
        return "redirect:/city/list?success=City deleted successfully";
    }
}