package com.greenbox.test.demo.entity.growParametrs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Points {
    private Long id;
    private String name;
    private List<Double> array;
}
