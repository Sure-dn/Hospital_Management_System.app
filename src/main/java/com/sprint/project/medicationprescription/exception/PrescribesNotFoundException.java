package com.sprint.project.medicationprescription.exception;

import com.sprint.project.medicationprescription.entity.PrescribesId;

public class PrescribesNotFoundException extends RuntimeException {

    public PrescribesNotFoundException(PrescribesId id) {
        super("Prescribes record not found for: " + id.toString());
    }
}