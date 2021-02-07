package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.GrowProgramRegistrationForm;
import com.greenbox.test.demo.controller.form.convert.WateringParametersForm;
import com.greenbox.test.demo.entity.growParametrs.WateringParameters;
import com.greenbox.test.demo.service.growParametrsServices.CommonPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/watering_parameters")
public class WateringParametersController {

    private final CommonPointsService<WateringParameters> wateringParametersCommonPointsService;

    @Autowired
    public WateringParametersController(CommonPointsService<WateringParameters> wateringParametersCommonPointsService) {
        this.wateringParametersCommonPointsService = wateringParametersCommonPointsService;
    }

    @GetMapping
    public String list(ModelMap modelMap){
        Set<WateringParameters> wateringParameters = wateringParametersCommonPointsService.readAll();
        modelMap.addAttribute("wateringParameters", wateringParameters);
        return "watering_parameters/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap modelMap){
        WateringParameters wateringParameter = wateringParametersCommonPointsService.read(id);
        modelMap.addAttribute("wateringParameter", wateringParameter);
        modelMap.addAttribute("wateringParametersForm", new WateringParametersForm());
        return "watering_parameters/view";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("wateringParametersForm") WateringParametersForm wateringParametersForm,
                       BindingResult result){
        if (result.hasErrors()) {
            return "grow_programs/add";
        }
        WateringParameters wateringParameters = new WateringParameters();
        wateringParameters.setName(wateringParametersForm.getName());
        wateringParameters.setVolume(wateringParametersForm.getVolume());
        wateringParameters.setInterval(wateringParametersForm.getInterval());
        wateringParametersCommonPointsService.update(wateringParameters, id);
        return "redirect:/watering_parameters" + '/' + id;
    }

    @GetMapping("/add")
    public String addGet(ModelMap modelMap){
        modelMap.addAttribute("wateringParametersForm", new WateringParametersForm());
        return "watering_parameters/add";
    }

    @PostMapping("/add")
    public String addPost(@Valid @ModelAttribute("wateringParametersForm") WateringParametersForm wateringParametersForm,
                          BindingResult result){
        if (result.hasErrors()) {
            return "grow_programs/add";
        }
        WateringParameters wateringParameters = new WateringParameters();
        wateringParameters.setName(wateringParametersForm.getName());
        wateringParameters.setVolume(wateringParametersForm.getVolume());
        wateringParameters.setInterval(wateringParametersForm.getInterval());
        wateringParametersCommonPointsService.create(wateringParameters);
        return "redirect:/watering_parameters";
    }

}
