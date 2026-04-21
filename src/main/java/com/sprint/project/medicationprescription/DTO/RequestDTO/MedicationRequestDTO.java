package com.sprint.project.medicationprescription.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicationRequestDTO {
    @NotNull private Integer code;
    @NotBlank private String name;
    @NotBlank private String brand;
    @NotBlank private String description;

    // constructors
    public MedicationRequestDTO() {}
    public MedicationRequestDTO(Integer code, String name, String brand, String description) {
        this.code = code; this.name = name; this.brand = brand; this.description = description;
    }
    // getters and setters
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
