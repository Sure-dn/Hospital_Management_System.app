package com.sprint.project.MedicationPrescriptionAPIs.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Medication")
public class MedicationEntity {

    @Id
    @NotNull
    @Column(name = "Code")
    private Integer code;

    @NotBlank
    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(name = "Brand", nullable = false, length = 100)
    private String brand;

    @NotBlank
    @Column(name = "Description", nullable = false, length = 500)
    private String description;

    public MedicationEntity() {}
    
    
    public MedicationEntity(@NotNull Integer code, @NotBlank String name, @NotBlank String brand,
			                    @NotBlank String description) {
		this.code = code;
		this.name = name;
		this.brand = brand;
		this.description = description;
	}

	@Override
	public String toString() {
		return "MedicationEntity [code=" + code + ", name=" + name + ", brand=" + brand + ", description=" + description
				+ "]";
	}

    
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
