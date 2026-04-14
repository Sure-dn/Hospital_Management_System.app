package com.sprint.project.EntityClasses;

import jakarta.persistence.*;

@Entity
@Table(name = "Medication")
public class Medication {

    @Id
    @Column(name = "Code")
    private Integer code;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Brand", nullable = false)
    private String brand;

    @Column(name = "Description", nullable = false)
    private String description;

    public Medication() {}

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}