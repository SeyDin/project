package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.growParametrs.TemperaturePoints;

import java.util.Set;

public interface TemperaturePointsService {
    void create(TemperaturePoints temperaturePoints);
    Set<TemperaturePoints> readAll();
    TemperaturePoints read(int id);
    void update(TemperaturePoints temperaturePoints, Long id);
}
