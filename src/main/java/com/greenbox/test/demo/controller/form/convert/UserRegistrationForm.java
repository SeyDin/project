package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationForm {

    @NotNull
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 15, message
            = "Name must be between 3 and 15 characters")
    private String username;

    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3, max = 15, message
            = "Password must be between 3 and 15 characters")
    private String password;

    private String email;

    @NotNull
    @NotEmpty(message = "Please confirm password")
    @Size(min = 3, max = 15)
    private String confirmPassword;
}
