package com.sprint.project.Treatment.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UndergoesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer patient;
    private Integer procedures;
    private Integer stay;
    private LocalDateTime dateUndergoes;

    public UndergoesId() {}

    public UndergoesId(Integer patient, Integer procedures, Integer stay, LocalDateTime dateUndergoes) {
        this.patient = patient;
        this.procedures = procedures;
        this.stay = stay;
        this.dateUndergoes = dateUndergoes;
    }

    // Getters and Setters

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

    // equals & hashCode (VERY IMPORTANT for composite key)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UndergoesId)) return false;
        UndergoesId that = (UndergoesId) o;
        return Objects.equals(patient, that.patient) &&
               Objects.equals(procedures, that.procedures) &&
               Objects.equals(stay, that.stay) &&
               Objects.equals(dateUndergoes, that.dateUndergoes);
    }

    @Override
	public String toString() {
		return "UndergoesId [patient=" + patient + ", procedures=" + procedures + ", stay=" + stay + ", dateUndergoes="
				+ dateUndergoes + "]";
	}

	@Override
    public int hashCode() {
        return Objects.hash(patient, procedures, stay, dateUndergoes);
    }
}