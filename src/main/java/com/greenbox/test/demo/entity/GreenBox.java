package com.greenbox.test.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "greenboxes")
@Data
public class GreenBox {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "grow_program_id")
    private GrowProgram growProgram;

}
