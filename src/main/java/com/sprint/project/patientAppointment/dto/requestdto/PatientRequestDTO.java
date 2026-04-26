package com.sprint.project.patientAppointment.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PatientRequestDTO {
	@NotNull(message = "SSN is required")
    private Integer ssn;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @NotNull(message = "Insurance ID is required")
    private Integer insuranceId;

    @NotNull(message = "PCP (Primary Care Physician) is required")
    private Integer pcp;
    
   // Getters and Setters
	public Integer getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Integer getPcp() {
		return pcp;
	}

	public void setPcp(Integer pcp) {
		this.pcp = pcp;
	}
    
}
