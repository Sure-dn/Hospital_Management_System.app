package com.sprint.project.patientAppointment.dto.responsedto;

public class PatientResponseDTO {
	private Integer ssn;
    private String name;
    private String address;
    private String phone;
    private Integer insuranceId;
    private Integer pcp;

    public PatientResponseDTO() {}

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
