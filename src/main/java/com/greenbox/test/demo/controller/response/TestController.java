package com.greenbox.test.demo.controller.response;

import com.greenbox.test.demo.controller.form.convert.GreenBoxRegistrationForm;
import com.greenbox.test.demo.controller.form.convert.PointsForm;
import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.CommonPointsService;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class TestController {
    private final TemperaturePointsService temperaturePointsService;
    private final Co2Service co2Service;

    @Autowired
    public TestController(TemperaturePointsService temperaturePointsService, Co2Service co2Service) {
        this.temperaturePointsService = temperaturePointsService;
        this.co2Service = co2Service;
    }

    @GetMapping("/test")
    public String index (ModelMap modelMap){
        return "test";
    }
    @GetMapping("/test2")
    public String index2 (ModelMap modelMap){
        return "test2";
    }

    @GetMapping("/test3")
    public String index3 (ModelMap modelMap){
        modelMap.addAttribute("points_form", new PointsForm());
        return "test3";
    }

    @PostMapping("/test3")
    public String input(@ModelAttribute("points_form") PointsForm pointsForm){
        System.out.println(pointsForm);
        return "test3";
    }

}

