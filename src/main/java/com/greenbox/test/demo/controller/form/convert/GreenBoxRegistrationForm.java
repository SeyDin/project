package com.greenbox.test.demo.controller.form.convert;

import javax.validation.constraints.NotNull;

public class GreenBoxRegistrationForm {

    private Integer id;
    @NotNull
    private String name;
    private Integer userId;
    private Integer growProgramId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
