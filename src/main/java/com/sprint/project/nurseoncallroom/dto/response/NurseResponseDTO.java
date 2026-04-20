package com.sprint.project.nurseoncallroom.dto.response;

public class NurseResponseDTO {

    private Integer employeeId;
    private String name;
    private String position;
    private Boolean registered;
    private Integer ssn;

    public NurseResponseDTO() {}

    public NurseResponseDTO(Integer employeeId, String name, String position,
                             Boolean registered, Integer ssn) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.registered = registered;
        this.ssn = ssn;
    }

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
