package com.greenbox.test.demo.controller.response;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestControllerRest {
    private final TemperaturePointsService temperaturePointsService;
    private final Co2Service co2Service;

    @Autowired
    public TestControllerRest(TemperaturePointsService temperaturePointsService, Co2Service co2Service) {
        this.temperaturePointsService = temperaturePointsService;
        this.co2Service = co2Service;
    }

    @GetMapping(value ={"/test_json"})
    public List<Double> test_json (ModelMap modelMap){
        Points tPoints = temperaturePointsService.read(1);
        return tPoints.getArray();
    }
}

