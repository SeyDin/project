package com.greenbox.test.demo.entity;

import com.greenbox.test.demo.entity.growParametrs.Points;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestEntityForBox {
    private List<Double> temperature;
    private List<Double> co2;
    private List<Double> light;
}
