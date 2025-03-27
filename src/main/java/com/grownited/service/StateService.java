package com.grownited.service;

import com.grownited.entity.StateEntity;
import com.grownited.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateEntity> getAllStates() {
        return stateRepository.findAll();
    }

    public void saveState(StateEntity state) {
        stateRepository.save(state);
    }

    // New method to fetch a state by ID
    public Optional<StateEntity> getStateById(Long stateId) {
        return stateRepository.findById(stateId);
    }

    // New method to delete a state
    public void deleteState(Long stateId) {
        stateRepository.deleteById(stateId);
    }
}