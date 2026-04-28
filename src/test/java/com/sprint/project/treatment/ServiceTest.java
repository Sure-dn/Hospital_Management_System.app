package com.sprint.project.treatment;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.nurseoncallroom.entity.RoomEntity;
import com.sprint.project.nurseoncallroom.repository.RoomRepository;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.patientAppointment.repository.PatientRepository;
import com.sprint.project.treatmentprostayy.dto.*;
import com.sprint.project.treatmentprostayy.entities.*;
import com.sprint.project.treatmentprostayy.exception.*;
import com.sprint.project.treatmentprostayy.services.*;

@ActiveProfiles("test")
@SpringBootTest
class ServiceTest {

    @Autowired
    private ProceduresService proceduresService;

    @Autowired
    private StayService stayService;

    @Autowired
    private UndergoesService undergoesService;

    // ---------- HELPERS ----------
    
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomRepository roomRepository;

    private void createPatient(int id) {
        PatientEntity p = new PatientEntity();
        p.setSsn(id);
        p.setName("Test");
        p.setAddress("Chennai");
        p.setPhone("9999999999");
        p.setInsuranceId(1);
        p.setPcp(1);
        patientRepository.save(p);
    }

    private void createRoom(int id) {
        RoomEntity r = new RoomEntity();
        r.setRoomNumber(id);
        r.setRoomType("GENERAL");
        r.setUnavailable(false);
        roomRepository.save(r);
    }
    private ProceduresRequestDTO procedureDTO(int code) {
        ProceduresRequestDTO dto = new ProceduresRequestDTO();
        dto.setCode(code);
        dto.setName("TestProc");
        dto.setCost(100.0);
        return dto;
    }

    private StayRequestDTO stayDTO(int stayId, int patientId, int roomId) {
        StayRequestDTO dto = new StayRequestDTO();
        dto.setStayId(stayId);
        dto.setPatientId(patientId);
        dto.setRoomId(roomId);
        dto.setStayStart(LocalDateTime.now().minusDays(1));
        return dto;
    }

    private UndergoesRequestDTO treatmentDTO(int p, int pr, int s) {
        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(p);
        dto.setProcedureId(pr);
        dto.setStayId(s);
        dto.setDateUndergoes(LocalDateTime.now());
        return dto;
    }

    // ==============================
    // 🟢 PROCEDURES TESTS
    // ==============================

    @Test
    void tc1_addProcedure_success() {
        ProceduresRequestDTO res = proceduresService.addProcedure(procedureDTO(1));
        assertNotNull(res);
    }

    @Test
    void tc2_addProcedure_duplicate() {
        proceduresService.addProcedure(procedureDTO(2));
        assertThrows(ProcedureAlreadyExistsException.class,
                () -> proceduresService.addProcedure(procedureDTO(2)));
    }

    @Test
    void tc3_addProcedure_invalid() {
        ProceduresRequestDTO dto = procedureDTO(3);
        dto.setCode(null);
        assertThrows(InvalidProcedureException.class,
                () -> proceduresService.addProcedure(dto));
    }

    @Test
    void tc4_getProcedure_notFound() {
        assertThrows(ProcedureNotFoundException.class,
                () -> proceduresService.getProcedureById(999));
    }

    @Test
    void tc5_deleteProcedure_success() {
        proceduresService.addProcedure(procedureDTO(5));
        assertNotNull(proceduresService.deleteProcedure(5));
    }

    // ==============================
    // 🟡 STAY TESTS
    // ==============================

    @Test
    void tc6_admitPatient_success() {
        StayEntity stay = stayService.admitPatient(stayDTO(10, 1, 1));
        assertNotNull(stay);
    }

    @Test
    void tc7_admitPatient_futureDate() {
        StayRequestDTO dto = stayDTO(11, 1, 1);
        dto.setStayStart(LocalDateTime.now().plusDays(1));

        assertThrows(InvalidStayException.class,
                () -> stayService.admitPatient(dto));
    }

    @Test
    void tc8_admitPatient_duplicate() {
        stayService.admitPatient(stayDTO(12, 1, 1));

        assertThrows(StayAlreadyExistsException.class,
                () -> stayService.admitPatient(stayDTO(12, 1, 1)));
    }

    @Test
    void tc9_getStay_notFound() {
        assertThrows(ResourceNotFoundException.class,
                () -> stayService.getStayById(999));
    }

    @Test
    void tc10_deleteStay_success() {
        stayService.admitPatient(stayDTO(13, 1, 1));
        String res = stayService.deleteStay(13);
        assertEquals("Stay deleted successfully", res);
    }

    // ==============================
    // 🔴 UNDERGOES TESTS
    // ==============================

    @Test
    void tc11_assignTreatment_success() {
        proceduresService.addProcedure(procedureDTO(20));
        stayService.admitPatient(stayDTO(21, 1, 1));

        UndergoesRequestDTO res =
                undergoesService.assignTreatment(treatmentDTO(1, 20, 21));

        assertNotNull(res);
    }

    @Test
    void tc12_assignTreatment_duplicate() {

        createPatient(1);
        createRoom(1);

        proceduresService.addProcedure(procedureDTO(22));
        stayService.admitPatient(stayDTO(23, 1, 1));

        LocalDateTime fixedTime = LocalDateTime.of(2024, 1, 1, 10, 0); // 🔥 SAME TIME

        UndergoesRequestDTO dto = new UndergoesRequestDTO();
        dto.setPatientId(1);
        dto.setProcedureId(22);
        dto.setStayId(23);
        dto.setDateUndergoes(fixedTime);

        undergoesService.assignTreatment(dto);

        assertThrows(TreatmentAlreadyExistsException.class,
                () -> undergoesService.assignTreatment(dto));
    }

    @Test
    void tc13_assignTreatment_invalidMismatch() {

        createPatient(1);   // for treatment
        createPatient(2);   // for stay
        createRoom(1);

        proceduresService.addProcedure(procedureDTO(24));

        stayService.admitPatient(stayDTO(25, 2, 1)); // patient 2

        assertThrows(InvalidTreatmentException.class,
                () -> undergoesService.assignTreatment(treatmentDTO(1, 24, 25)));
    }

    @Test
    void tc14_assignTreatment_patientNotFound() {
        assertThrows(TreatmentNotFoundException.class,
                () -> undergoesService.assignTreatment(treatmentDTO(999, 1, 1)));
    }

    @Test
    void tc15_deleteTreatment_notFound() {
        UndergoesId id = new UndergoesId(1,1,1,LocalDateTime.now());

        assertThrows(TreatmentNotFoundException.class,
                () -> undergoesService.deleteTreatment(id));
    }
}