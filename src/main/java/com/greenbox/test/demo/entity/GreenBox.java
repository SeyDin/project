package com.greenbox.test.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "greenboxes")
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
    private Integer growProgramId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getGrowProgramId() {
        return growProgramId;
    }

    public void setGrowProgramId(Integer growProgramId) {
        this.growProgramId = growProgramId;
    }
}
