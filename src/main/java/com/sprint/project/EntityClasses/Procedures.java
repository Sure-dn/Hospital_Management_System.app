package com.sprint.project.EntityClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Procedures {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long procedureId;
	    private String procedureName;
	    private Double cost;
		public Long getProcedureId() {
			return procedureId;
		}
		public void setProcedureId(Long procedureId) {
			this.procedureId = procedureId;
		}
		public String getProcedureName() {
			return procedureName;
		}
		public void setProcedureName(String procedureName) {
			this.procedureName = procedureName;
		}
		public Double getCost() {
			return cost;
		}
		public void setCost(Double cost) {
			this.cost = cost;
		}

}

