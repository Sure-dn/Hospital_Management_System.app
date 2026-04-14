package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Trained_In {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Doctor name cannot be empty")
    private String doctorName;

    @NotBlank(message = "Specialization cannot be empty")
    private String specialization;

    // Relationship
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Procedures> procedures;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Procedures> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedures> procedures) {
        this.procedures = procedures;
    }
}

