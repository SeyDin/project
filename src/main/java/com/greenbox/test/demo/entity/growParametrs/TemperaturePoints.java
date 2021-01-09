package com.greenbox.test.demo.entity.growParametrs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemperaturePoints {
    private Long id;
    private String name;
    private double arrayOfTemperature;
}
