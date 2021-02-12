package com.greenbox.test.demo.controller.valid;

import com.greenbox.test.demo.controller.form.convert.GrowProgramRegistrationForm;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.GrowProgramRepository;
import com.greenbox.test.demo.repository.UserRepository;
import com.greenbox.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class GrowProgramRegistrationFormValidator implements Validator {
    private final UserService userService;
    private final GrowProgramRepository growProgramRepository;

    @Autowired
    public GrowProgramRegistrationFormValidator(UserService userService, GrowProgramRepository growProgramRepository) {
        this.userService = userService;
        this.growProgramRepository = growProgramRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GrowProgramRegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        GrowProgramRegistrationForm growProgramRegistrationForm = (GrowProgramRegistrationForm) o;

    }
}
