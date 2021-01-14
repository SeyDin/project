package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GrowProgramRegistrationForm {

    private Long id;
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @Size(min = 3, max = 255)
    private String description;

    private Long wateringParametersId;
    private Long co2Id;
    private Long lightId;
    private Long temperatureId;

}
