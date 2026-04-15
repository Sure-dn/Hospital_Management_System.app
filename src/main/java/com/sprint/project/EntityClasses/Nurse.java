package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "nurse")
public class Nurse {

    @Id
    @NotNull(message = "Employee ID is required")
    private Integer employeeID;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Position is required")
    private String position;

    public Nurse() {}

    public Nurse(Integer employeeID, String name, String gender, String position) {
        this.employeeID = employeeID;
        this.name = name;
        this.gender = gender;
        this.position = position;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}