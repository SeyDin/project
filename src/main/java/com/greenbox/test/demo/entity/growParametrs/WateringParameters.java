package com.greenbox.test.demo.entity.growParametrs;

import com.greenbox.test.demo.entity.GreenBox;
import com.greenbox.test.demo.entity.GrowProgram;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "watering_parameters")
@Data
public class WateringParameters {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "interval")
    private Long interval;

    @Column(name = "volume")
    private Long volume;

    @OneToMany
    @JoinColumn(name = "watering_parameters_id")
    private List<GrowProgram> greenBoxList;
}
