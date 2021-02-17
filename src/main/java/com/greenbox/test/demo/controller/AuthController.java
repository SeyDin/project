package com.greenbox.test.demo.controller;


import com.greenbox.test.demo.controller.form.convert.UserRegistrationForm;
import com.greenbox.test.demo.controller.valid.UserRegistrationFormValidator;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.service.GrowProgramService;
import com.greenbox.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final GrowProgramService growProgramService;
    private final UserRegistrationFormValidator userRegistrationFormValidator;

    @Autowired
    public AuthController(UserService userService, GrowProgramService growProgramService, UserRegistrationFormValidator userRegistrationFormValidator) {
        this.userService = userService;
        this.growProgramService = growProgramService;
        this.userRegistrationFormValidator = userRegistrationFormValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }

    @GetMapping("/registration")
    public String registration(ModelMap modelMap) {
        modelMap.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid  @ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm,
            BindingResult bindingResult, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("userRegistrationForm", userRegistrationForm);
            return "auth/registration";
        }

        User user = new User();
        user.setUsername(userRegistrationForm.getUsername());
        user.setPassword(userRegistrationForm.getPassword());
        user.setEmail(userRegistrationForm.getEmail());

        userService.signupUser(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "auth/login";
    }


    @GetMapping("/profile")
    public String profile(ModelMap modelMap) {
        User currentUser = userService.getCurrentUser();
        List<GrowProgram> growPrograms = growProgramService.findFavoritesProgramsForUser(currentUser);
        modelMap.addAttribute("growPrograms", growPrograms);
        return "auth/profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {

        return "auth/admin";
    }


}
