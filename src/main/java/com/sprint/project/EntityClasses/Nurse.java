package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Nurse")
public class Nurse {

    @Id
    @Column(name = "EmployeeID")
    @NotNull
    private Integer employeeId;

    @Column(name = "Name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "Position", nullable = false)
    @NotBlank
    private String position;

    @Column(name = "Registered", nullable = false)
    @NotNull
    private Boolean registered;

    @Column(name = "SSN", nullable = false)
    @NotNull
    private Integer ssn;

    public Nurse() {}

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