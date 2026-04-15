package com.sprint.project.Treatment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Procedures")
public class Procedures {

    @Id
    @NotNull
    @Column(name = "Code")
    private Integer code;

    @NotBlank(message = "Procedure name cannot be empty")
    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Cost cannot be empty")
    @Column(name = "Cost", nullable = false, length = 50)
    private Double cost;

    public Procedures() {}

    // Getters and Setters

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(double d) {
        this.cost = d;
    }
}

