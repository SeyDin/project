package com.greenbox.test.demo.controller.form.convert;

import javax.validation.constraints.NotNull;

public class GreenBoxRegistrationForm {

    private Long id;
    @NotNull
    private String name;
    private Long userId;
    private Integer growProgramId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Integer getGrowProgramId() {
        return growProgramId;
    }

    public void setGrowProgramId(Integer growProgramId) {
        this.growProgramId = growProgramId;
    }

    @Override
    public String toString() {
        return "GreenBoxRegistrationForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", growProgramId=" + growProgramId +
                '}';
    }
}
