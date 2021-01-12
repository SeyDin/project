package com.greenbox.test.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "greenboxes")
@Data
public class GreenBox {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "grow_program_id")
    private Long growProgramId;

}
