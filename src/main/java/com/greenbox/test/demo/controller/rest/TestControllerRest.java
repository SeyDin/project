package com.greenbox.test.demo.controller.rest;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @GetMapping(value ={"/test_json2"})
    public List<Double> test_json2 (ModelMap modelMap){
        Points tPoints = temperaturePointsService.read(1);
        return tPoints.getArray();
    }

    @PostMapping(value = "/api/growbox/uploadconfig", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?>  input(@RequestBody String stringList){
        System.out.println(stringList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

