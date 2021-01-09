package com.greenbox.test.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "water_interval_id")
    private Long waterIntervalId;

    @Column(name = "water_volume_id")
    private Long waterVolumeId;

    @OneToMany
    @JoinColumn(name = "grow_program_id")
    private List<GreenBox> greenBoxList;

}
