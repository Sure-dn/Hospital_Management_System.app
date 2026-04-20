package com.sprint.project.patientAppointment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.List;

@Entity
@Table(name = "Patient")
public class PatientEntity {

    @Id
    @NotNull
    @Column(name = "SSN")
    private Integer ssn;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Address cannot be empty")
    @Column(name = "Address", nullable = false, length = 200)
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    @Column(name = "Phone", nullable = false, length = 20)
    private String phone;

    @NotNull(message = "Insurance ID is required")
    @Column(name = "InsuranceID", nullable = false)
    private Integer insuranceId;

    @NotNull(message = "PCP (Primary Care Physician) is required")
    @Column(name = "PCP", nullable = false)
    private Integer pcp;

    @OneToMany(mappedBy = "patient")
    private List<AppointmentEntity> appointments;

    public PatientEntity() {}

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

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}

