package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.GreenBoxRegistrationForm;
import com.greenbox.test.demo.controller.response.ResourceNotFoundException;
import com.greenbox.test.demo.entity.GreenBox;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.RestEntityForBox;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.GreenBoxRepository;
import com.greenbox.test.demo.service.GrowProgramService;
import com.greenbox.test.demo.service.UserService;
import com.greenbox.test.demo.service.growParametrsServices.Co2Service;
import com.greenbox.test.demo.service.growParametrsServices.LightPointsService;
import com.greenbox.test.demo.service.growParametrsServices.TemperaturePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/green_boxes")
public class GreenBoxController {

    private final UserService userService;
    private final GreenBoxRepository greenBoxRepository;
    private final GrowProgramService growProgramService;
    private final TemperaturePointsService temperaturePointsService;
    private final Co2Service co2Service;
    private final LightPointsService lightPointsService;

    @Autowired
    public GreenBoxController(UserService userService, GreenBoxRepository greenBoxRepository,
                              GrowProgramService growProgramService, TemperaturePointsService temperaturePointsService,
                              Co2Service co2Service, LightPointsService lightPointsService) {
        this.userService = userService;
        this.greenBoxRepository = greenBoxRepository;
        this.growProgramService = growProgramService;
        this.temperaturePointsService = temperaturePointsService;
        this.co2Service = co2Service;
        this.lightPointsService = lightPointsService;
    }


    @GetMapping
    public String list(ModelMap modelMap){
        User user = userService.getCurrentUser();
        Long id =  user.getId();
        List<GreenBox> greenBoxList = greenBoxRepository.findAllByUserId(id);
        List<GrowProgram> growProgramsList = new ArrayList<>();
        if (greenBoxList.size() != 0){
            for (GreenBox greenBox : greenBoxList
            ) {
                growProgramsList.add(growProgramService.read(greenBox.getGrowProgram().getId()).orElseThrow(ResourceNotFoundException::new));
            }
        }
        modelMap.addAttribute("growPrograms", growProgramsList);
        modelMap.addAttribute("greenBoxes", greenBoxList);
        return "green_boxes/list";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {

        User user = userService.getCurrentUser();
        modelMap.addAttribute("growPrograms", growProgramService.readAll());
        modelMap.addAttribute("gb_form", new GreenBoxRegistrationForm());
        //modelMap.addAttribute("currentUserName", user.getUsername());

        return "green_boxes/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("gb_form") GreenBoxRegistrationForm greenBoxRegistrationForm,
                      BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            greenBoxRegistrationForm.setName(greenBoxRegistrationForm.getName());
            modelMap.addAttribute("growPrograms", growProgramService.readAll());
            return "green_boxes/add";
        }

        User currentUser = userService.getCurrentUser();
        if (Objects.nonNull(greenBoxRegistrationForm.getId())) {
            GreenBox read = greenBoxRepository.findById(greenBoxRegistrationForm.getId()).orElseThrow(ResourceNotFoundException::new);
            User userCreator = read.getUser();
            if (!currentUser.equals(userCreator)) {
                return "error/403";
            }
        }
        GreenBox greenBox = new GreenBox();
        greenBox.setId(greenBoxRegistrationForm.getId());
        greenBox.setName(greenBoxRegistrationForm.getName());
        greenBox.setGrowProgram(growProgramService.read(greenBoxRegistrationForm.getGrowProgramId()).orElseThrow(ResourceNotFoundException::new));
        greenBox.setUser(currentUser);

        greenBoxRepository.save(greenBox);

        //Sending data

        GrowProgram growProgram = greenBox.getGrowProgram();
        RestEntityForBox restEntityForBox = new RestEntityForBox();
        restEntityForBox.setCo2(co2Service.read(growProgram.getCo2Id()).getArray());
        restEntityForBox.setLight(lightPointsService.read(growProgram.getLightId()).getArray());
        restEntityForBox.setTemperature(temperaturePointsService.read(growProgram.getTemperatureId()).getArray());

        final RestTemplate restTemplate = new RestTemplate();
        final RestEntityForBox insertedPost = restTemplate.postForObject("http://localhost:8080/api/growbox/uploadconfig", restEntityForBox, RestEntityForBox.class);
        System.out.println(insertedPost);
        return "redirect:/green_boxes";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap modelMap) {
        GreenBox greenBox = greenBoxRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        User user = userService.getCurrentUser();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("greenBox", greenBox);
        return "green_boxes/view";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        GreenBox greenBox = greenBoxRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        GreenBoxRegistrationForm greenBoxRegistrationForm = new GreenBoxRegistrationForm();
        greenBoxRegistrationForm.setId(id);
        greenBoxRegistrationForm.setName(greenBox.getName());
        greenBoxRegistrationForm.setGrowProgramId(greenBox.getGrowProgram().getId());
        modelMap.addAttribute("growPrograms", growProgramService.readAll());
        modelMap.addAttribute("gb_form", greenBoxRegistrationForm);
        return "green_boxes/add";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        User currentUser = userService.getCurrentUser();
        GreenBox read = greenBoxRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        User userCreator = read.getUser();
        if (!currentUser.equals(userCreator)) {
            return "error/403";
        }
        greenBoxRepository.delete(read);
        return "redirect:/green_boxes";
    }


}
