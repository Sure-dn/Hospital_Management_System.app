package com.sprint.project.EntityClasses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UndergoesId implements Serializable {

    private Integer patient;
    private Integer procedures;
    private Integer stay;
    private LocalDateTime dateUndergoes;

    // ✅ Default Constructor
    public UndergoesId() {}

    // ✅ Parameterized Constructor
    public UndergoesId(Integer patient, Integer procedures, Integer stay, LocalDateTime dateUndergoes) {
        this.patient = patient;
        this.procedures = procedures;
        this.stay = stay;
        this.dateUndergoes = dateUndergoes;
    }

    // ✅ Getters and Setters

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getProcedures() {
        return procedures;
    }

    public void setProcedures(Integer procedures) {
        this.procedures = procedures;
    }

    public Integer getStay() {
        return stay;
    }

    public void setStay(Integer stay) {
        this.stay = stay;
    }

    public LocalDateTime getDateUndergoes() {
        return dateUndergoes;
    }

    public void setDateUndergoes(LocalDateTime dateUndergoes) {
        this.dateUndergoes = dateUndergoes;
    }

    // ✅ equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UndergoesId that = (UndergoesId) o;

        return Objects.equals(patient, that.patient) &&
               Objects.equals(procedures, that.procedures) &&
               Objects.equals(stay, that.stay) &&
               Objects.equals(dateUndergoes, that.dateUndergoes);
    }

    // ✅ hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(patient, procedures, stay, dateUndergoes);
    }
}
