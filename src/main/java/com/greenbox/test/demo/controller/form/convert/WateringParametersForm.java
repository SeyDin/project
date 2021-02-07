package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

@Data
public class WateringParametersForm {
    private Long id;
    private String name;
    private Long interval;
    private Long volume;
}
