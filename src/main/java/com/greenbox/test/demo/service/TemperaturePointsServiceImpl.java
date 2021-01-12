package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.growParametrs.TemperaturePoints;
import com.greenbox.test.demo.repository.TemperaturePointsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TemperaturePointsServiceImpl implements TemperaturePointsService{

    private final TemperaturePointsRepository temperaturePointsRepository;

    public TemperaturePointsServiceImpl(TemperaturePointsRepository temperaturePointsRepository) {
        this.temperaturePointsRepository = temperaturePointsRepository;
    }

    @Override
    public void create(TemperaturePoints temperaturePoints) {
        temperaturePointsRepository.save(temperaturePoints);
    }

    @Override
    public Set<TemperaturePoints> readAll() {
        return temperaturePointsRepository.findAll();
    }

    @Override
    public TemperaturePoints read(int id) {
        return temperaturePointsRepository.findOne(id);
    }

    @Override
    public void update(TemperaturePoints temperaturePoints, Long id) {
        temperaturePoints.setId(id);
        temperaturePointsRepository.update(temperaturePoints);
    }
}
