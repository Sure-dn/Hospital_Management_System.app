package com.sprint.project.medicationprescription.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PrescribesId implements Serializable {

    private Integer physician;
    private Integer patient;
    private Integer medication;
    private LocalDateTime date;

    public PrescribesId() {}

    public PrescribesId(Integer physician, Integer patient, Integer medication, LocalDateTime date) {
        this.physician = physician;
        this.patient = patient;
        this.medication = medication;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrescribesId)) return false;
        PrescribesId that = (PrescribesId) o;
        return Objects.equals(physician, that.physician) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(medication, that.medication) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(physician, patient, medication, date);
    }
}