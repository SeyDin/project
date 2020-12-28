package com.greenbox.test.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "greenboxes")
public class GreenBox {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "grow_program_id")
    private Integer growProgramId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGrowProgramId() {
        return growProgramId;
    }

    public void setGrowProgramId(Integer growProgramId) {
        this.growProgramId = growProgramId;
    }
}
