package com.greenbox.test.demo.entity;

import com.greenbox.test.demo.entity.growParametrs.WateringParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "grow_programs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrowProgram {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "watering_parameters_id")
    private WateringParameters wateringParameters;

    @OneToMany(mappedBy = "growProgram")
    private List<GreenBox> greenBoxList;

    @Column(name = "co2_id")
    private Long co2Id;
    @Column(name = "light_id")
    private Long lightId;
    @Column(name = "temperature_id")
    private Long temperatureId;

    @ManyToOne
    @JoinColumn(name = "user_creator_id")
    private User userCreator;

}
