package com.sprint.project.nurseoncallroom.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NurseRequestDTO {



        @NotNull(message = "Employee ID is required")
        private Integer employeeId;


        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 50)
        private String name;

        @NotBlank(message = "Position is required")
        private String position;

        @NotNull(message = "Registered status is required")
        private Boolean registered;

        @NotNull(message = "SSN is required")
        private Integer ssn;

        public NurseRequestDTO() {}

        // Getters & Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }

        public Boolean getRegistered() { return registered; }
        public void setRegistered(Boolean registered) { this.registered = registered; }

        public Integer getSsn() { return ssn; }
        public void setSsn(Integer ssn) { this.ssn = ssn; }

        public Integer getEmployeeId() {
                return employeeId;
        }
        public void setEmployeeId(Integer employeeId){
                this.employeeId=employeeId;
        }
}