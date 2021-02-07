package com.greenbox.test.demo.controller.rest;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.CommonPointsService;
import com.greenbox.test.demo.service.growParametrsServices.LightPointsService;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/rest/points")
public class RestPointsController {
    private final Map<String, CommonPointsService<Points>> serviceMap = new HashMap<>();

    @Autowired
    public RestPointsController(TemperaturePointsService temperaturePointsService, LightPointsService lightPointsService, Co2Service co2Service) {
        this.serviceMap.put("temperature", temperaturePointsService);
        this.serviceMap.put("light", lightPointsService);
        this.serviceMap.put("co2", co2Service);
    }

    @GetMapping("/{type}/{id}")
    public List<Double> test_json (@PathVariable String type, @PathVariable Long id){
        if (serviceMap.containsKey(type)) {
            CommonPointsService<Points> currentPointsService = serviceMap.get(type);
            Points points = currentPointsService.read(id);
            if (Objects.nonNull(points)) {
                return points.getArray();
            }
        }
        return new ArrayList<>();
    }


}

