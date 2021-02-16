package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GreenBoxRegistrationForm {

    private Long id;
    @Size(min = 3, max = 255)
    private String name;
    private Long userId;
    private Long growProgramId;

}
