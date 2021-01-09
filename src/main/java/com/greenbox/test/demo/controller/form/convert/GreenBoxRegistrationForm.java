package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GreenBoxRegistrationForm {

    private Long id;
    @NotNull
    private String name;
    private Long userId;
    private Integer growProgramId;

}
