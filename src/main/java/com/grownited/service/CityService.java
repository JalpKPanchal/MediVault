package com.grownited.service;

import com.grownited.entity.CityEntity;
import com.grownited.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityEntity> getAllCities() {
        return cityRepository.findAll();
    }

    public void saveCity(CityEntity city) {
        cityRepository.save(city);
    }

    // New method to fetch a city by ID
    public Optional<CityEntity> getCityById(Long cityId) {
        return cityRepository.findById(cityId);
    }

    // New method to delete a city
    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}