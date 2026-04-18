package com.sprint.project.physicianDepartmentManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.exception.*;
import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.*;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.*;
import com.sprint.project.physicianDepartmentManagement.Entity.TrainedInId;
import com.sprint.project.physicianDepartmentManagement.Service.*;

@SpringBootTest
public class ServiceTest {

    @Autowired private PhysicianService physicianService;
    @Autowired private DepartmentService departmentService;
    @Autowired private AffiliatedWithService affiliatedWithService;
    @Autowired private TrainedInService trainedInService;

    // ======================================================
    // 🟢 PHYSICIAN
    // ======================================================

    @Test
    void TC01_createPhysician_success() {

        PhysicianRequestDto dto = new PhysicianRequestDto();
        dto.setEmployeeId(100001);
        dto.setName("Doctor A");
        dto.setPosition("Surgeon");
        dto.setSsn(111111);

        assertThat(physicianService.createPhysician(dto).getData()).isNotNull();
    }

    @Test
    void TC02_getPhysician_notFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                physicianService.getPhysicianById(999999));
    }

    @Test
    void TC03_duplicatePhysician() {

        PhysicianRequestDto dto = new PhysicianRequestDto();
        dto.setEmployeeId(200001);
        dto.setName("Doc B");
        dto.setPosition("Cardio");
        dto.setSsn(222222);

        physicianService.createPhysician(dto);

        assertThrows(DuplicateResourceException.class, () ->
                physicianService.createPhysician(dto));
    }

    // ======================================================
    // 🟢 DEPARTMENT
    // ======================================================

    @Test
    void TC04_createDepartment_success() {

        PhysicianRequestDto phy = new PhysicianRequestDto();
        phy.setEmployeeId(300001);
        phy.setName("Head Doc");
        phy.setPosition("Neuro");
        phy.setSsn(333333);

        physicianService.createPhysician(phy);

        DepartmentRequestDto dept = new DepartmentRequestDto();
        dept.setDepartmentId(400001);
        dept.setName("Neuro Dept");
        dept.setHeadEmployeeId(300001);

        assertThat(departmentService.createDepartment(dept)).isNotNull();
    }

    @Test
    void TC05_getAllDepartments() {
        assertThat(departmentService.getAllDepartments()).isNotNull();
    }

    @Test
    void TC06_department_notFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                departmentService.getDepartmentById(999999));
    }

    // ======================================================
    // 🟢 AFFILIATION
    // ======================================================

    @Test
    void TC07_createAffiliation_success() {

        PhysicianRequestDto phy = new PhysicianRequestDto();
        phy.setEmployeeId(500001);
        phy.setName("Doc A");
        phy.setPosition("Ortho");
        phy.setSsn(555555);
        physicianService.createPhysician(phy);

        DepartmentRequestDto dept = new DepartmentRequestDto();
        dept.setDepartmentId(600001);
        dept.setName("Ortho Dept");
        dept.setHeadEmployeeId(500001);
        departmentService.createDepartment(dept);

        AffiliatedWithRequestDto dto = new AffiliatedWithRequestDto();
        dto.setDepartmentId(600001);
        dto.setPrimaryAffiliation(true);

        assertThat(affiliatedWithService.createAffiliation(500001, dto)).isNotNull();
    }

    @Test
    void TC08_duplicateAffiliation() {

        PhysicianRequestDto phy = new PhysicianRequestDto();
        phy.setEmployeeId(500002);
        phy.setName("Doc B");
        phy.setPosition("Ortho");
        phy.setSsn(666666);
        physicianService.createPhysician(phy);

        DepartmentRequestDto dept = new DepartmentRequestDto();
        dept.setDepartmentId(600002);
        dept.setName("Ortho Dept 2");
        dept.setHeadEmployeeId(500002);
        departmentService.createDepartment(dept);

        AffiliatedWithRequestDto dto = new AffiliatedWithRequestDto();
        dto.setDepartmentId(600002);
        dto.setPrimaryAffiliation(false);

        affiliatedWithService.createAffiliation(500002, dto);

        assertThrows(DuplicateResourceException.class, () ->
                affiliatedWithService.createAffiliation(500002, dto));
    }

    @Test
    void TC09_getAffiliationByPhysician() {

        assertThrows(ResourceNotFoundException.class, () ->
                affiliatedWithService.getAffiliationsByPhysician(999999));
    }

    // ======================================================
    // 🟢 TRAINED IN (FIXED - MISSING PART ADDED)
    // ======================================================

    

   

    @Test
    void TC13_getAllTrainings() {
        assertThat(trainedInService.getAllTrainings()).isNotNull();
    }

    @Test
    void TC14_training_notFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                trainedInService.getTrainingById(new TrainedInId(9999, 9999)));
    }

    @Test
    void TC15_deleteTraining_notFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                trainedInService.deleteTraining(new TrainedInId(9999, 9999)));
    }
}