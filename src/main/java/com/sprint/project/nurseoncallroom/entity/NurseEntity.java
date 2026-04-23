package com.sprint.project.nurseoncallroom.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Nurse")
public class NurseEntity {

    @Id
    @NotNull(message = "Employee ID is required")
    @Column(name = "EmployeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Position is required")
    @Column(name = "Position", nullable = false, length = 50)
    private String position;

    @NotNull(message = "Registered status is required")
    @Column(name = "Registered", nullable = false)
    private Boolean registered;

    @NotNull(message = "SSN is required")
    @Column(name = "SSN", unique = true, nullable = false)
    private Integer ssn;

    public NurseEntity() {}



    @Override
    public String toString() {
        return "NurseEntity{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", registered=" + registered +
                ", ssn=" + ssn +
                '}';
    }

    public NurseEntity(Integer employeeId, String name, String position, Boolean registered, Integer ssn) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.registered = registered;
        this.ssn = ssn;
    }


// Getters and Setters

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }
}

