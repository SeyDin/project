package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.controller.form.convert.GreenBoxRegistrationForm;
import com.greenbox.test.demo.controller.form.convert.GrowProgramRegistrationForm;
import com.greenbox.test.demo.entity.GreenBox;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.service.GreenBoxServiceImpl;
import com.greenbox.test.demo.service.GrowProgramServiceImpl;
import com.greenbox.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/grow_programs")
public class GrowProgramController {

    private final UserService userService;
    private final GrowProgramServiceImpl growProgramService;

    @Autowired
    public GrowProgramController(UserService userService, GrowProgramServiceImpl growProgramService) {
        this.userService = userService;
        this.growProgramService = growProgramService;
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
        modelMap.addAttribute("program", growProgram);
        return "grow_programs/view";
    }
    @GetMapping("/add")
    public String add(ModelMap modelMap){
        User user = userService.getCurrentUser();

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
        growProgram.setWaterVolumeId(growProgramRegistrationForm.getWaterVolumeId());
        growProgram.setWaterIntervalId(growProgramRegistrationForm.getWaterIntervalId());
        //User user = userService.getCurrentUser();
        //Long id =  user.getId();
        //growProgram.setUserId(id);
        growProgramService.create(growProgram);

        return "redirect:/grow_programs";
    }


}
