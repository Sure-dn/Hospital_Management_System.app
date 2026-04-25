package com.sprint.project.physicianDepartmentManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.exception.DuplicateResourceException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.exception.ValidationException;
import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.*;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.*;
import com.sprint.project.physicianDepartmentManagement.entity.*;
import com.sprint.project.physicianDepartmentManagement.repository.*;
import com.sprint.project.treatmentprostayy.entities.ProceduresEntity;
import com.sprint.project.treatmentprostayy.repositories.ProceduresRepository;
import com.sprint.project.physicianDepartmentManagement.Service.*;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ServiceTest {

    @Autowired 
    private PhysicianService physicianService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private AffiliatedWithService affiliatedWithService;
    
    @Autowired
    private TrainedInService trainedInService;
    
    @Autowired 
    private ProceduresRepository proceduresRepository;

   
    //  HELPERS (NO DB DEPENDENCY)
 

    private int createPhysician(int id) {
        PhysicianRequestDto dto = new PhysicianRequestDto();
        dto.setEmployeeId(id);
        dto.setName("Doctor " + id);
        dto.setPosition("Surgeon");
        dto.setSsn(id + 1000);

        return physicianService.createPhysician(dto).getData().getEmployeeId();
    }

    private int createDepartment(int deptId, int physicianId) {
        DepartmentRequestDto dto = new DepartmentRequestDto();
        dto.setDepartmentId(deptId);
        dto.setName("Dept " + deptId);
        dto.setHeadEmployeeId(physicianId);

        return departmentService.createDepartment(dto).getDepartmentId();
    }

    private int createProcedure(int code) {
        ProceduresEntity p = new ProceduresEntity();
        p.setCode(code);
        p.setName("Procedure " + code);

        return proceduresRepository.save(p).getCode();
    }

   
    //  PHYSICIAN TESTS


    @Test
    void TC01_createPhysician_success() {

        int id = createPhysician(10001);

        assertThat(id).isEqualTo(10001);
    }

    @Test
    void TC02_getPhysician_notFound() {

    	assertThrows(com.sprint.project.physicianDepartmentManagement.exception.PhysicianNotFoundException.class, () -> {
    	    physicianService.getPhysicianById(99999);
    	});
    }

    @Test
    void TC03_duplicatePhysician() {

        createPhysician(20001);

        assertThrows(com.sprint.project.physicianDepartmentManagement.exception.DuplicatePhysicianException.class, () -> {
            createPhysician(20001);
        });
    }

   
    //  DEPARTMENT TESTS
  

    @Test
    void TC04_createDepartment_success() {

        int physId = createPhysician(30001);
        int deptId = createDepartment(40001, physId);

        assertThat(deptId).isEqualTo(40001);
    }

    @Test
    void TC05_getDepartmentList() {

        List<DepartmentResponseDto> list = departmentService.getAllDepartments();

        assertThat(list).isNotNull();
    }

    @Test
    void TC06_department_notFound() {

    	assertThrows(com.sprint.project.physicianDepartmentManagement.exception.DepartmentNotFoundExcpetion.class, () -> {
    	    departmentService.getDepartmentById(99999);
    	});
    }

   
    //  AFFILIATED WITH TESTS
  

    @Test
    void TC07_createAffiliation_success() {

        int physId = createPhysician(50001);
        int deptId = createDepartment(60001, physId);

        AffiliatedWithRequestDto dto = new AffiliatedWithRequestDto();
        dto.setDepartmentId(deptId);
        dto.setPrimaryAffiliation(true);

        AffiliatedWithResponseDto res =
                affiliatedWithService.createAffiliation(physId, dto);

        assertThat(res).isNotNull();
    }

    @Test
    void TC08_duplicateAffiliation() {

        int physId = createPhysician(70001);
        int deptId = createDepartment(80001, physId);

        AffiliatedWithRequestDto dto = new AffiliatedWithRequestDto();
        dto.setDepartmentId(deptId);
        dto.setPrimaryAffiliation(true);

        affiliatedWithService.createAffiliation(physId, dto);

        assertThrows(com.sprint.project.physicianDepartmentManagement.exception.DuplicateAffiliationException.class, () -> {
            affiliatedWithService.createAffiliation(physId, dto);
        });
    }

    @Test
    void TC09_getAffiliations() {

        int physId = createPhysician(90001);

        List<AffiliatedWithResponseDto> list =
                affiliatedWithService.getAffiliationsByPhysician(physId);

        assertThat(list).isNotNull();
    }

    // TRAINED IN TESTS
  

    @Test
    void TC10_createTraining_success() {

        int physId = createPhysician(11001);
        int procId = createProcedure(12001);

        TrainedInRequestDto dto = new TrainedInRequestDto();
        dto.setPhysicianId(physId);
        dto.setTreatmentCode(procId);
        dto.setCertificationExpiry(LocalDate.now().plusDays(10));

        TrainedInResponseDto res = trainedInService.createTraining(dto);

        assertThat(res).isNotNull();
    }

    @Test
    void TC11_training_invalidDate() {

        int physId = createPhysician(13001);
        int procId = createProcedure(14001);

        TrainedInRequestDto dto = new TrainedInRequestDto();
        dto.setPhysicianId(physId);
        dto.setTreatmentCode(procId);
        dto.setCertificationExpiry(LocalDate.now().minusDays(1));

        assertThrows(ValidationException.class, () -> {
            trainedInService.createTraining(dto);
        });
    }

    @Test
    void TC12_getAllTrainings() {

        List<TrainedInResponseDto> list = trainedInService.getAllTrainings();

        assertThat(list).isNotNull();
    }

    @Test
    void TC13_training_notFound() {

        TrainedInId id = new TrainedInId(999, 999);

        assertThrows(com.sprint.project.physicianDepartmentManagement.exception.TrainingNotFoundException.class, () -> {
            trainedInService.getTrainingById(id);
        });
    }

    @Test
    void TC14_deleteTraining_notFound() {

        TrainedInId id = new TrainedInId(999, 999);

        assertThrows(com.sprint.project.physicianDepartmentManagement.exception.TrainingNotFoundException.class, () -> {
            trainedInService.deleteTraining(id);
        });
    }

    //  SYSTEM TEST
    // 

    @Test
    void TC15_getAllPhysicians() {

        var res = physicianService.getAllPhysicians();

        assertThat(res.getData()).isNotNull();
    }
}