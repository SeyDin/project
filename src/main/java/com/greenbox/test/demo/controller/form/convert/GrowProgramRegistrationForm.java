package com.greenbox.test.demo.controller.form.convert;

import com.greenbox.test.demo.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GrowProgramRegistrationForm {

    private Long id;
    @Size(min = 3, max = 30)
    private String name;
    @Size(min = 3, max = 255)
    private String description;

    private Long wateringParametersId;
    private Long co2Id;
    private Long lightId;
    private Long temperatureId;
    private User userCreator;

}
