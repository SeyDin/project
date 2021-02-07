package com.greenbox.test.demo.controller.form.convert;

import lombok.Data;

import java.util.List;

@Data
public class PointsForm {
    private Long id;
    private String name;
    private List<Double> array;
}
