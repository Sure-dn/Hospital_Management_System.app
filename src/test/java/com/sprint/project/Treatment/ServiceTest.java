package com.sprint.project.Treatment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.Entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.Repository.NurseRepository;
import com.sprint.project.NurseOnCallRoomAPIs.Repository.RoomRepository;
import com.sprint.project.Treatment.Entity.*;
import com.sprint.project.Treatment.Repository.ProceduresRepository;
import com.sprint.project.Treatment.Repository.StayRepository;
import com.sprint.project.Treatment.Service.*;
import com.sprint.project.exception.*;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ProceduresRepository proceduresRepository;
	@Autowired
	private StayRepository stayRepository;
	@Autowired
	private PhysicianRepository physicianRepository;
	@Autowired
	private NurseRepository nurseRepository;
	@Autowired
    private ProceduresService proceduresService;

    @Autowired
    private StayService stayService;

    @Autowired
    private UndergoesService undergoesService;

    // =========================================
    // ✅ PROCEDURES SERVICE TEST
    // =========================================

    @Test
    void testAddProcedure_Success() {

        ProceduresEntity p = new ProceduresEntity();
        p.setCode(9999);  // ✅ NEW UNIQUE VALUE
        p.setName("Test Procedure");
        p.setCost(500.0);

        ProceduresEntity saved = proceduresService.addProcedure(p);

        assertThat(saved).isNotNull();
    }

    @Test
    void testAddProcedure_Duplicate() {
        ProceduresEntity p = new ProceduresEntity();
        p.setCode(1); // already exists in DB

        assertThrows(DuplicateResourceException.class, () -> {
            proceduresService.addProcedure(p);
        });
    }

    @Test
    void testGetProcedure_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            proceduresService.getProcedureById(99999);
        });
    }

    @Test
    void testGetAllProcedures() {
        List<ProceduresEntity> list = proceduresService.getAllProcedures();
        assertThat(list).isNotEmpty();
    }

    // =========================================
    // ✅ STAY SERVICE TEST
    // =========================================

    @Test
    void testAdmitPatient_InvalidDate() {

        StayEntity stay = new StayEntity();
        stay.setStayId(999);

        // future date → invalid
        stay.setStayStart(LocalDateTime.now().plusDays(1));

        assertThrows(BadRequestException.class, () -> {
            stayService.admitPatient(stay);
        });
    }

    @Test
    void testGetStay_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            stayService.getStayById(99999);
        });
    }

    @Test
    void testGetAllStays() {

        PatientEntity patient = patientRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No patient found"));

        RoomEntity room = roomRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No room found"));

        StayEntity stay = new StayEntity();
        stay.setStayId(999);
        stay.setPatient(patient);
        stay.setRoom(room);
        stay.setStayStart(LocalDateTime.now());

        stayService.admitPatient(stay);

        List<StayEntity> list = stayService.getAllStays();

        assertThat(list).isNotEmpty();
    }

    // =========================================
    // ✅ UNDERGOES SERVICE TEST
    // =========================================

    @Test
    void testGetAllTreatments() {

        // ✅ Get existing entities
        PatientEntity patient = patientRepository.findAll()
                .stream().findFirst()
                .orElseThrow();

        ProceduresEntity procedure = proceduresRepository.findAll()
                .stream().findFirst()
                .orElseThrow();

        StayEntity stay = stayRepository.findAll()
                .stream().findFirst()
                .orElseThrow();

        PhysicianEntity physician = physicianRepository.findAll()
                .stream().findFirst()
                .orElseThrow();

        NurseEntity nurse = nurseRepository.findAll()
                .stream().findFirst()
                .orElseThrow();

        // ✅ Create Undergoes
        UndergoesId id = new UndergoesId(
                patient.getSsn(),
                procedure.getCode(),
                stay.getStayId(),
                LocalDateTime.now()
        );

        UndergoesEntity entity = new UndergoesEntity(
                id, patient, procedure, stay, physician, nurse
        );

        undergoesService.assignTreatment(entity); // insert

        // ✅ Now test
        List<UndergoesEntity> list = undergoesService.getAllTreatments();

        assertThat(list).isNotEmpty();
    }

    @Test
    void testGetTreatmentsByPatient() {
        List<UndergoesEntity> list = undergoesService.getTreatmentByPatient(100000001);
        assertThat(list).isNotNull();
    }

    @Test
    void testGetTreatmentsByStay() {
        List<UndergoesEntity> list = undergoesService.getTreatmentByStay(3215);
        assertThat(list).isNotNull();
    }

    @Test
    void testDeleteTreatment_NotFound() {

        UndergoesId id = new UndergoesId(
                999,
                999,
                999,
                LocalDateTime.now()
        );

        assertThrows(ResourceNotFoundException.class, () -> {
            undergoesService.deleteTreatment(id);
        });
    }
}
