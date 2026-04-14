package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Nurse")
public class Nurse {

    @Id
    private Integer employeeID;

    @NotBlank
    private String name;

    @NotBlank
    private String position;

    @NotNull
    private Boolean registered;

    @NotNull
    private Integer ssn;

    // Constructors
    public Nurse() {}

    public Nurse(Integer employeeID, String name, String position, Boolean registered, Integer ssn) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.registered = registered;
        this.ssn = ssn;
    }

    // Getters and Setters
    public Integer getEmployeeID() { return employeeID; }
    public void setEmployeeID(Integer employeeID) { this.employeeID = employeeID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Boolean getRegistered() { return registered; }
    public void setRegistered(Boolean registered) { this.registered = registered; }

    public Integer getSsn() { return ssn; }
    public void setSsn(Integer ssn) { this.ssn = ssn; }
}