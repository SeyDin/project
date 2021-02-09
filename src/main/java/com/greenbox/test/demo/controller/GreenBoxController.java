package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.GreenBoxRegistrationForm;
import com.greenbox.test.demo.controller.response.ResourceNotFoundException;
import com.greenbox.test.demo.entity.GreenBox;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.GreenBoxRepository;
import com.greenbox.test.demo.service.GrowProgramService;
import com.greenbox.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/green_boxes")
public class GreenBoxController {

    private final UserService userService;
    private final GreenBoxRepository greenBoxRepository;
    private final GrowProgramService growProgramService;

    @Autowired
    public GreenBoxController(UserService userService, GreenBoxRepository greenBoxRepository, GrowProgramService growProgramService) {
        this.userService = userService;
        this.greenBoxRepository = greenBoxRepository;
        this.growProgramService = growProgramService;
    }

    @GetMapping
    public String list(ModelMap modelMap){
        User user = userService.getCurrentUser();
        Long id =  user.getId();
        List<GreenBox> greenBoxList = greenBoxRepository.findAllByUserId(id);
        List<GrowProgram> growProgramsList = new ArrayList<>();
        for (GreenBox greenBox:greenBoxList
             ) {
            growProgramsList.add(growProgramService.read(greenBox.getGrowProgramId()));
        }
        modelMap.addAttribute("growPrograms", growProgramsList);
        modelMap.addAttribute("greenBoxes", greenBoxList);
        return "green_boxes/list";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {

        User user = userService.getCurrentUser();
        Long id = user.getId();
        modelMap.addAttribute("growPrograms", growProgramService.readAll());
        modelMap.addAttribute("gb_form", new GreenBoxRegistrationForm());
        modelMap.addAttribute("currentUserName", user.getUsername());

        return "green_boxes/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("gb_form") GreenBoxRegistrationForm greenBoxRegistrationForm,
                      BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "green_boxes/add";
        }

        GreenBox greenBox = new GreenBox();
        greenBox.setName(greenBoxRegistrationForm.getName());
        greenBox.setGrowProgramId(greenBoxRegistrationForm.getGrowProgramId());
        User user = userService.getCurrentUser();
        Long id =  user.getId();
        greenBox.setUserId(id);
        greenBoxRepository.save(greenBox);

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
}
