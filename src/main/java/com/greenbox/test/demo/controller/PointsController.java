package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.PointsForm;
import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.CommonPointsService;
import com.greenbox.test.demo.service.growParametrsServices.LightPointsService;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/points")
public class PointsController {
    private final Map<String, CommonPointsService<Points>> serviceMap = new HashMap<>();

    @Autowired
    public PointsController(TemperaturePointsService temperaturePointsService, LightPointsService lightPointsService, Co2Service co2Service) {
        this.serviceMap.put("temperature", temperaturePointsService);
        this.serviceMap.put("light", lightPointsService);
        this.serviceMap.put("co2", co2Service);
    }

    @GetMapping("/{type}")
    public String list(@PathVariable String type, ModelMap modelMap){
        if (serviceMap.containsKey(type)){
            CommonPointsService<Points> currentPointsService = serviceMap.get(type);
            modelMap.addAttribute("type", type);
            Set<Points> pointsSet = currentPointsService.readAll();
            modelMap.addAttribute("pointsSet", pointsSet);
            return "points/list";
        }
        return "error/404";
    }

    @GetMapping("/{type}/{id}")
    public String view(@PathVariable String type, @PathVariable Long id, ModelMap modelMap){
        if (serviceMap.containsKey(type)){
            CommonPointsService<Points> currentPointsService = serviceMap.get(type);
            Points points = currentPointsService.read(id);
            if (Objects.nonNull(points)) {
                String typeForView = Character.toUpperCase(type.charAt(0)) + type.substring(1);
                modelMap.addAttribute("type", typeForView);
                modelMap.addAttribute("points", points);
                modelMap.addAttribute("points_form", new PointsForm());
                return "points/view";
            }
            return "error/404";
        }
        return "error/404";
    }

    @PostMapping("/{type}/{id}")
    public String input(@PathVariable String type, @PathVariable Long id,
                        @ModelAttribute("points_form") PointsForm pointsForm){
        if (serviceMap.containsKey(type)){
            CommonPointsService<Points> currentPointsService = serviceMap.get(type);
            Points points = new Points();
            points.setName(pointsForm.getName());
            points.setArray(pointsForm.getArray());
            currentPointsService.update(points, pointsForm.getId());
            return "redirect:/points/" + type + '/' + id;
        }
        return "error/404";
    }

    @GetMapping("/{type}/add")
    public String addGet(@PathVariable String type, ModelMap modelMap){
        if (serviceMap.containsKey(type)) {
            CommonPointsService<Points> currentPointsService = serviceMap.get(type);
            String typeForView = Character.toUpperCase(type.charAt(0)) + type.substring(1);
            modelMap.addAttribute("type", typeForView);

            Points points = new Points();
            List<Double> doubles = new ArrayList<>(Arrays.asList(
                    20.0, 20.0, 20.0, 20.0, 20.0, 20.0,
                    20.0, 20.0, 20.0, 20.0, 20.0, 20.0,
                    20.0, 20.0, 20.0, 20.0, 20.0, 20.0,
                    20.0, 20.0, 20.0, 20.0, 20.0, 20.0));
            points.setArray(doubles);
            modelMap.addAttribute("points", points);
            modelMap.addAttribute("points_form", new PointsForm());
            return "points/view";
        }
        return "error/404";
    }

    @PostMapping("/{type}/add")
    public String addPost(@PathVariable String type,
                          @ModelAttribute("points_form") PointsForm pointsForm){
        if (serviceMap.containsKey(type)) {
            CommonPointsService<Points> currentPointsService = serviceMap.get(type);
            Points points = new Points();
            points.setName(pointsForm.getName());
            points.setArray(pointsForm.getArray());
            currentPointsService.create(points);
            return "redirect:/points/" + type;
        }
        return "error/404";
    }


}
