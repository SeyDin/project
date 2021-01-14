package com.greenbox.test.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "grow_programs")
@Data
public class GrowProgram {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "watering_parameters_id")
    private Long wateringParametersId;

    @OneToMany
    @JoinColumn(name = "grow_program_id")
    private List<GreenBox> greenBoxList;

    @Column(name = "co2_id")
    private Long co2Id;
    @Column(name = "light_id")
    private Long lightId;
    @Column(name = "temperature_id")
    private Long temperatureId;

    @ManyToMany()
    @JoinTable(
            name = "FAVORITE_PROGRAMS_ASSIGNMENTS",
            joinColumns = @JoinColumn(name = "grow_program_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"))
    private Set<User> users;

}
