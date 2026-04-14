package com.sprint.project.EntityClasses;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(PrescribesId.class)
public class Prescribes {

    @Id
    @ManyToOne
    @JoinColumn(name = "Physician")
    private Physician physician;

    @Id
    @ManyToOne
    @JoinColumn(name = "Patient")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "Medication")
    private Medication medication;

    @Id
    @Column(name = "Date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Appointment")
    private Appointment appointment;

    @Column(name = "Dose", nullable = false)
    private String dose;

    // Getters and Setters
}
