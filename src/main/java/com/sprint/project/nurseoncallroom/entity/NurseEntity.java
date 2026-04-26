package com.sprint.project.nurseoncallroom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Nurse")
public class NurseEntity {

    @Id
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "position", nullable = false)
    private String position;

    @NotNull
    @Column(name = "registered", nullable = false)
    private Boolean registered;

    @NotNull
    @Column(name = "ssn", unique = true, nullable = false)
    private Integer ssn;

    public NurseEntity() {}

    public NurseEntity(Integer employeeId, String name, String position, Boolean registered, Integer ssn) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.registered = registered;
        this.ssn = ssn;
    }

    // Getters & Setters
    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Boolean getRegistered() { return registered; }
    public void setRegistered(Boolean registered) { this.registered = registered; }

    public Integer getSsn() { return ssn; }
    public void setSsn(Integer ssn) { this.ssn = ssn; }
}