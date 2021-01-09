package com.greenbox.test.demo.entity.growParametrs;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "water_intervals")
@Data
public class WaterInterval {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "interval")
    private Long interval;
}
