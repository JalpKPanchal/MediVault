package com.grownited.controller;

import com.grownited.entity.StateEntity;
import com.grownited.service.StateService;
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
    public String listStates(Model model) {
        List<StateEntity> states = stateService.getAllStates();
        model.addAttribute("states", states);
        return "StateList";  // Updated to use StateList.jsp
    }

    // Show form to add a new state
    @GetMapping("/new")
    public String newStateForm(Model model) {
        model.addAttribute("stateEntity", new StateEntity());
        return "StateForm";  // Updated to use StateForm.jsp
    }

    // Create or update state
    @PostMapping("/save")
    public String saveState(@ModelAttribute StateEntity stateEntity) {
        stateService.saveState(stateEntity);
        return "redirect:/state/list";
    }

    // Show form to update a state
    @GetMapping("/edit/{stateId}")
    public String editState(@PathVariable Integer stateId, Model model) {
        Optional<StateEntity> state = stateService.getStateById(stateId);
        if (state.isPresent()) {
            model.addAttribute("stateEntity", state.get());
            return "StateForm";  // Updated to use StateForm.jsp
        }
        return "redirect:/state/list";
    }

    // Delete state
    @GetMapping("/delete/{stateId}")
    public String deleteState(@PathVariable Integer stateId) {
        stateService.deleteState(stateId);
        return "redirect:/state/list";
    }
}
