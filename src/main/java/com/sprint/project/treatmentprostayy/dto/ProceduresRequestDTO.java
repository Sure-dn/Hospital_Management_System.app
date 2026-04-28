package com.sprint.project.treatmentprostayy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProceduresRequestDTO {

    @NotNull(message = "Procedure code is required")
    private Integer code;

    @NotBlank(message = "Procedure name is required")
    private String name;

    @NotNull(message = "Procedure cost is required")
    @Positive
    private Double cost;

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

	public void setCost(Double cost) {
		this.cost = cost;
	}
}