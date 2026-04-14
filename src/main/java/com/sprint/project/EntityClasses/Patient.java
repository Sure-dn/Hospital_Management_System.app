package com.sprint.project.EntityClasses;

<<<<<<< HEAD

=======
>>>>>>> 63978cb3aee9f86847b360190a3d00d9ca08873b
import jakarta.persistence.*;

@Entity
public class Patient {

    @Id
    private int ssn;

    private String name;
    private String address;
    private String phone;
    private int insuranceId;

    @ManyToOne
    @JoinColumn(name = "PCP")
    private Physician physician;

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 63978cb3aee9f86847b360190a3d00d9ca08873b
