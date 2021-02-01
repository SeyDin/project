package com.greenbox.test.demo.controller.valid;

import com.greenbox.test.demo.controller.form.convert.UserRegistrationForm;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserRegistrationFormValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationFormValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegistrationForm userForm = (UserRegistrationForm) o;
        User savedUser = userRepository.findByUsername(userForm.getUsername());
        if (Objects.nonNull(savedUser) && savedUser.getUsername().equals(userForm.getUsername())) {
            errors.rejectValue("username", "user.username.nonUnique");
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "user.confirmPassword.nonEquals");
        }
    }
}
