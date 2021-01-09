package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

@Data
public class UserRegistrationForm {

    private String username;
    private String password;
    private String email;
    private String confirmPassword;

}
