package com.sprint.project.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Department")
public class Department {
	@Id
    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "Name", nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "Head", nullable = false)
    private Physician head;

}
