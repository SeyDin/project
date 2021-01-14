package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.GrowProgramRegistrationForm;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.entity.growParametrs.WateringParameters;
import com.greenbox.test.demo.service.GrowProgramService;
import com.greenbox.test.demo.service.UserService;
import com.greenbox.test.demo.service.growParametrsServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/grow_programs")
public class GrowProgramController {

    private final UserService userService;
    private final GrowProgramService growProgramService;
    private final CommonPointsService<WateringParameters> wateringParametersService;
    private final Co2Service co2Service;
    private final TemperaturePointsService temperaturePointsService;
    private final LightPointsService lightPointsService;

    @Autowired
    public GrowProgramController(UserService userService, GrowProgramService growProgramService,
                                 CommonPointsService<WateringParameters> wateringParametersService,
                                 Co2Service co2Service, TemperaturePointsService temperaturePointsService,
                                 LightPointsService lightPointsService) {
        this.userService = userService;
        this.growProgramService = growProgramService;
        this.wateringParametersService = wateringParametersService;
        this.co2Service = co2Service;
        this.temperaturePointsService = temperaturePointsService;
        this.lightPointsService = lightPointsService;
    }

    @GetMapping
    public String list(ModelMap modelMap){
        List<GrowProgram> growPrograms = growProgramService.readAll();
        modelMap.addAttribute("growPrograms", growPrograms);
        return "grow_programs/list";
    }
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap modelMap){
        GrowProgram growProgram = growProgramService.read(id);
        modelMap.addAttribute("grow_program", growProgram);
        return "grow_programs/view";
    }
    @GetMapping("/add")
    public String add(ModelMap modelMap){
        User user = userService.getCurrentUser();
        Set<WateringParameters> wateringParameters = wateringParametersService.readAll();
        Set<Points> lightParameters = lightPointsService.readAll();
        Set<Points> temperatureParameters = temperaturePointsService.readAll();
        Set<Points> co2Parameters = co2Service.readAll();
        modelMap.addAttribute("co2Parameters", co2Parameters);
        modelMap.addAttribute("temperatureParameters", temperatureParameters);
        modelMap.addAttribute("lightParameters", lightParameters);
        modelMap.addAttribute("wateringParameters", wateringParameters);
        modelMap.addAttribute("gp_form", new GrowProgramRegistrationForm());
        modelMap.addAttribute("currentUserName", user.getUsername());

        return "grow_programs/add";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("gp_form") GrowProgramRegistrationForm growProgramRegistrationForm,
                      BindingResult result){
        if (result.hasErrors()) {
            return "grow_programs/add";
        }

        GrowProgram growProgram = new GrowProgram();
        growProgram.setName(growProgramRegistrationForm.getName());
        growProgram.setDescription(growProgramRegistrationForm.getDescription());
        growProgram.setWateringParametersId(growProgramRegistrationForm.getWateringParametersId());
        growProgram.setTemperatureId(growProgramRegistrationForm.getTemperatureId());
        growProgram.setLightId(growProgramRegistrationForm.getLightId());
        growProgram.setCo2Id(growProgramRegistrationForm.getCo2Id());
        //User user = userService.getCurrentUser();
        //Long id =  user.getId();
        //growProgram.setUserId(id);
        growProgramService.create(growProgram);

        return "redirect:/grow_programs";
    }


}
