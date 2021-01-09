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

    @Column(name = "water_interval_id")
    private Long waterIntervalId;

    @Column(name = "water_volume_id")
    private Long waterVolumeId;

    @OneToMany
    @JoinColumn(name = "grow_program_id")
    private List<GreenBox> greenBoxList;

    @ManyToMany()
    @JoinTable(
            name = "FAVORITE_PROGRAMS_ASSIGNMENTS",
            joinColumns = @JoinColumn(name = "grow_program_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"))
    private Set<User> users;

}
