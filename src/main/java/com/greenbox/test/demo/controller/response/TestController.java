package com.greenbox.test.demo.controller.response;

import com.greenbox.test.demo.entity.growParametrs.TemperaturePoints;
import com.greenbox.test.demo.repository.TemperaturePointsRepository;
import com.greenbox.test.demo.service.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Controller
public class TestController {
    private final TemperaturePointsRepository temperaturePointsRepository;

    @Autowired
    public TestController(TemperaturePointsRepository temperaturePointsRepository) {
        this.temperaturePointsRepository = temperaturePointsRepository;
    }

    @GetMapping(value ={"/test"})
    public String index (ModelMap modelMap){
        TemperaturePoints temperaturePoints = new TemperaturePoints();
        temperaturePoints.setId(2L);
        temperaturePoints.setName("wow");
        List<Double> doubleList = new ArrayList<>(Arrays.asList(1.0,2.5,3.4));
        temperaturePoints.setArrayOfTemperature(doubleList);
        temperaturePointsRepository.update(temperaturePoints);
        return "test";
    }
}

