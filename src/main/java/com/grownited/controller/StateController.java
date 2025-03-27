package com.grownited.controller;

import com.grownited.entity.StateEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.StateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    // Show all states
    @GetMapping("/list")
    public String listStates(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        List<StateEntity> states = stateService.getAllStates();
        model.addAttribute("states", states);
        return "StateList";  // Using Thymeleaf template (StateList.html)
    }

    // Show form to add a new state
    @GetMapping("/new")
    public String newStateForm(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        model.addAttribute("stateEntity", new StateEntity());
        return "StateForm";  // Using Thymeleaf template (StateForm.html)
    }

    // Create or update state
    @PostMapping("/save")
    public String saveState(@ModelAttribute StateEntity stateEntity, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        stateService.saveState(stateEntity);
        return "redirect:/state/list?success=State saved successfully";
    }

    // Show form to update a state
    @GetMapping("/edit/{stateId}")
    public String editState(@PathVariable Long stateId, Model model, HttpSession session) { // Changed UUID to Long
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        Optional<StateEntity> state = stateService.getStateById(stateId);
        if (state.isPresent()) {
            model.addAttribute("stateEntity", state.get());
            return "StateForm";
        }
        return "redirect:/state/list?error=State not found";
    }

    // Delete state
    @GetMapping("/delete/{stateId}")
    public String deleteState(@PathVariable Long stateId, HttpSession session) { // Changed UUID to Long
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        stateService.deleteState(stateId);
        return "redirect:/state/list?success=State deleted successfully";
    }
}