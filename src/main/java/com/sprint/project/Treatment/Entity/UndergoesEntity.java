package com.sprint.project.Treatment.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.NurseEntity;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

@Entity
@Table(name = "Undergoes")
public class UndergoesEntity {

    @EmbeddedId
    @Valid   // 🔥 Important: validates fields inside EmbeddedId
    private UndergoesId undergoesId;

    @NotNull(message = "Patient cannot be null")
    @ManyToOne
    @MapsId("patient")
    @JoinColumn(name = "Patient", referencedColumnName = "SSN")
    private PatientEntity patient;

    @NotNull(message = "Procedure cannot be null")
    @ManyToOne
    @MapsId("procedures")
    @JoinColumn(name = "Procedures", referencedColumnName = "Code")
    private ProceduresEntity procedures;

    @NotNull(message = "Stay cannot be null")
    @ManyToOne
    @MapsId("stay")
    @JoinColumn(name = "Stay", referencedColumnName = "StayID")
    private StayEntity stay;

    @ManyToOne
    @JoinColumn(name = "Physician", referencedColumnName = "EmployeeID")
    private PhysicianEntity physician;

    @ManyToOne
    @JoinColumn(name = "AssistingNurse", referencedColumnName = "EmployeeID")
    private NurseEntity assistingNurse;

    public UndergoesEntity() {}

    public UndergoesEntity(UndergoesId undergoesId,
                           PatientEntity patient,
                           ProceduresEntity procedures,
                           StayEntity stay,
                           PhysicianEntity physician,
                           NurseEntity assistingNurse) {
        this.undergoesId = undergoesId;
        this.patient = patient;
        this.procedures = procedures;
        this.stay = stay;
        this.physician = physician;
        this.assistingNurse = assistingNurse;
    }

    // Getters & Setters

    public UndergoesId getUndergoesId() { return undergoesId; }
    public void setUndergoesId(UndergoesId undergoesId) { this.undergoesId = undergoesId; }

    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }

    public ProceduresEntity getProcedures() { return procedures; }
    public void setProcedures(ProceduresEntity procedures) { this.procedures = procedures; }

    public StayEntity getStay() { return stay; }
    public void setStay(StayEntity stay) { this.stay = stay; }

    public PhysicianEntity getPhysician() { return physician; }
    public void setPhysician(PhysicianEntity physician) { this.physician = physician; }

    public NurseEntity getAssistingNurse() { return assistingNurse; }
    public void setAssistingNurse(NurseEntity assistingNurse) { this.assistingNurse = assistingNurse; }

	@Override
	public String toString() {
		return "UndergoesEntity [undergoesId=" + undergoesId + ", patient=" + patient + ", procedures=" + procedures
				+ ", stay=" + stay + ", physician=" + physician + ", assistingNurse=" + assistingNurse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(assistingNurse, patient, physician, procedures, stay, undergoesId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UndergoesEntity other = (UndergoesEntity) obj;
		return Objects.equals(assistingNurse, other.assistingNurse) && Objects.equals(patient, other.patient)
				&& Objects.equals(physician, other.physician) && Objects.equals(procedures, other.procedures)
				&& Objects.equals(stay, other.stay) && Objects.equals(undergoesId, other.undergoesId);
	}
    
    
}