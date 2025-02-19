package com.grownited.service;

import com.grownited.entity.StateEntity;
import com.grownited.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<StateEntity> getAllStates() {
        return stateRepository.findAll();
    }

    public Optional<StateEntity> getStateById(UUID stateId) {
        return stateRepository.findById(stateId);
    }

    public void saveState(StateEntity stateEntity) {
        stateRepository.save(stateEntity);
    }

    public void deleteState(UUID stateId) {
        stateRepository.deleteById(stateId);
    }
}
