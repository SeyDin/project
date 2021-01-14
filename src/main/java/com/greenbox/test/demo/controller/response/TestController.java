package com.greenbox.test.demo.controller.response;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.CommonPointsService;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
    private final TemperaturePointsService temperaturePointsService;
    private final Co2Service co2Service;

    @Autowired
    public TestController(TemperaturePointsService temperaturePointsService, Co2Service co2Service) {
        this.temperaturePointsService = temperaturePointsService;
        this.co2Service = co2Service;
    }

    @GetMapping(value ={"/test"})
    public String index (ModelMap modelMap){
        return "test";
    }
}

