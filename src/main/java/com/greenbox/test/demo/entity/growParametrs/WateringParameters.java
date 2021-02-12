package com.greenbox.test.demo.entity.growParametrs;

import com.greenbox.test.demo.entity.GrowProgram;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "watering_parameters")
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

    @OneToMany(mappedBy = "wateringParameters")  //если я оставляю эту строку hibernate при изменении конкретного параметра сбрасывает ссылку у GrowProgram ссылающегося на этот парметр. Какого?
    private List<GrowProgram> greenBoxList;
}
