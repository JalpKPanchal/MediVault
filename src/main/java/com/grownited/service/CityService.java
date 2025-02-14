package com.grownited.service;

import com.grownited.entity.CityEntity;
import com.grownited.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // Get all cities
    public List<CityEntity> getAllCities() {
        return cityRepository.findAll();
    }

    // Get city by ID
    public Optional<CityEntity> getCityById(Integer cityId) {
        return cityRepository.findById(cityId);
    }

    // Save city (create or update)
    public void saveCity(CityEntity cityEntity) {
        cityRepository.save(cityEntity);
    }

    // Delete city
    public void deleteCity(Integer cityId) {
        cityRepository.deleteById(cityId);
    }
}
