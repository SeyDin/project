package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.GrowProgramRegistrationForm;
import com.greenbox.test.demo.controller.response.ResourceNotFoundException;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public GrowProgramController(UserService userService,
                                 GrowProgramService growProgramService,
                                 CommonPointsService<WateringParameters> wateringParametersService,
                                 TemperaturePointsService temperaturePointsService,
                                 LightPointsService lightPointsService,
                                 Co2Service co2Service) {
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
        growPrograms.sort(Comparator.comparing(GrowProgram::getId));
        modelMap.addAttribute("growPrograms", growPrograms);
        Map<Long, List<Points>> integerListHashMap = new HashMap<>();
        for (GrowProgram growProgram:growPrograms
             ) {
            List<Points> pointsList = new ArrayList<>();
            pointsList.add(temperaturePointsService.read(growProgram.getTemperatureId()));
            pointsList.add(lightPointsService.read(growProgram.getLightId()));
            pointsList.add(co2Service.read(growProgram.getCo2Id()));

            integerListHashMap.put(growProgram.getId(), pointsList);
        }
        modelMap.addAttribute("map", integerListHashMap);
        return "grow_programs/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap modelMap){
        GrowProgram growProgram = growProgramService.read(id).orElseThrow(ResourceNotFoundException::new);
        modelMap.addAttribute("grow_program", growProgram);
        final Points lightParameters = lightPointsService.read(growProgram.getLightId());
        final Points temperatureParameters = temperaturePointsService.read(growProgram.getTemperatureId());
        final Points co2Parameters = co2Service.read(growProgram.getLightId());
        final WateringParameters wateringParameters = wateringParametersService.read(growProgram.getWateringParameters().getId());
        modelMap.addAttribute("lightParameters", lightParameters);
        modelMap.addAttribute("temperatureParameters", temperatureParameters);
        modelMap.addAttribute("co2Parameters", co2Parameters);
        modelMap.addAttribute("wateringParameters", wateringParameters);
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
                      BindingResult result, ModelMap modelMap){
        if (result.hasErrors()) {
            User user = userService.getCurrentUser();
            Set<WateringParameters> wateringParameters = wateringParametersService.readAll();
            Set<Points> lightParameters = lightPointsService.readAll();
            Set<Points> temperatureParameters = temperaturePointsService.readAll();
            Set<Points> co2Parameters = co2Service.readAll();
            modelMap.addAttribute("co2Parameters", co2Parameters);
            modelMap.addAttribute("temperatureParameters", temperatureParameters);
            modelMap.addAttribute("lightParameters", lightParameters);
            modelMap.addAttribute("wateringParameters", wateringParameters);
            modelMap.addAttribute("currentUserName", user.getUsername());
            return "grow_programs/add";
        }
        User currentUser = userService.getCurrentUser();
        if (Objects.nonNull(growProgramRegistrationForm.getId())) {
            GrowProgram read = growProgramService.read(growProgramRegistrationForm.getId()).orElseThrow(ResourceNotFoundException::new);
            User userCreator = read.getUserCreator();
            if (!currentUser.equals(userCreator)) {
                return "error/403";
            }
        }
        GrowProgram growProgram = new GrowProgram();
        growProgram.setId(growProgramRegistrationForm.getId());
        growProgram.setName(growProgramRegistrationForm.getName());
        growProgram.setDescription(growProgramRegistrationForm.getDescription());
        growProgram.setWateringParameters(wateringParametersService.read(growProgramRegistrationForm.getWateringParametersId()));
        growProgram.setTemperatureId(growProgramRegistrationForm.getTemperatureId());
        growProgram.setLightId(growProgramRegistrationForm.getLightId());
        growProgram.setCo2Id(growProgramRegistrationForm.getCo2Id());

        growProgram.setUserCreator(currentUser);
        growProgramService.create(growProgram);

        return "redirect:/grow_programs";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap){
        GrowProgram growProgram = growProgramService.read(id).orElseThrow(ResourceNotFoundException::new);
        GrowProgramRegistrationForm growProgramRegistrationForm = new GrowProgramRegistrationForm();
        growProgramRegistrationForm.setId(growProgram.getId());
        growProgramRegistrationForm.setName(growProgram.getName());
        growProgramRegistrationForm.setDescription(growProgram.getDescription());
        growProgramRegistrationForm.setWateringParametersId(growProgram.getWateringParameters().getId());
        growProgramRegistrationForm.setTemperatureId(growProgram.getTemperatureId());
        growProgramRegistrationForm.setLightId(growProgram.getLightId());
        growProgramRegistrationForm.setCo2Id(growProgram.getCo2Id());
        final Set<WateringParameters> wateringParameters = wateringParametersService.readAll();
        final Set<Points> lightParameters = lightPointsService.readAll();
        final Set<Points> temperatureParameters = temperaturePointsService.readAll();
        final Set<Points> co2Parameters = co2Service.readAll();
        modelMap.addAttribute("lightParameters", lightParameters);
        modelMap.addAttribute("temperatureParameters", temperatureParameters);
        modelMap.addAttribute("co2Parameters", co2Parameters);
        modelMap.addAttribute("wateringParameters", wateringParameters);
        modelMap.addAttribute("gp_form", growProgramRegistrationForm);
        return "grow_programs/edit";
    }

    /*@PostMapping("/{id}/edit")
    public String edit_post(@PathVariable Long id,
                            @Valid @ModelAttribute("gp_form") GrowProgramRegistrationForm growProgramRegistrationForm,
                            BindingResult result){
        if (result.hasErrors()) {
            return "grow_programs/add";
        }
        GrowProgram growProgram = growProgramService.read(id).orElseThrow(ResourceNotFoundException::new);
        growProgram.setName(growProgramRegistrationForm.getName());
        growProgram.setDescription(growProgramRegistrationForm.getDescription());
        growProgram.setWateringParameters(wateringParametersService.read(growProgramRegistrationForm.getWateringParametersId()));
        growProgram.setTemperatureId(growProgramRegistrationForm.getTemperatureId());
        growProgram.setLightId(growProgramRegistrationForm.getLightId());
        growProgram.setCo2Id(growProgramRegistrationForm.getCo2Id());
        User user = userService.getCurrentUser();
        growProgram.setUserCreator(user);
        growProgramService.create(growProgram);

        return "redirect:/grow_programs/" + id;
    }*/

}
