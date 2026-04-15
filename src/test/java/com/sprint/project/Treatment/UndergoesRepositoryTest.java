package com.sprint.project.Treatment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.Entity.RoomEntity;
import com.sprint.project.Treatment.Entity.ProceduresEntity;
import com.sprint.project.Treatment.Entity.StayEntity;
import com.sprint.project.Treatment.Entity.UndergoesEntity;
import com.sprint.project.Treatment.Entity.UndergoesId;
import com.sprint.project.Treatment.Repository.UndergoesRepository;
import com.sprint.project.patientAppointment.Entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;

@DataJpaTest
public class UndergoesRepositoryTest {

    @Autowired
    private UndergoesRepository undergoesRepository;

    @Autowired
    private TestEntityManager entityManager;

    private PatientEntity patient;
    private ProceduresEntity procedure;
    private StayEntity stay;
    private PhysicianEntity physician;
    private NurseEntity nurse;
    private UndergoesId undergoesId;

    @BeforeEach
    void setUp() {
        undergoesRepository.deleteAll();

        // Persist dependent entities
        patient = new PatientEntity();
        patient.setSsn(2001);
        patient.setName("Jane Smith");
        entityManager.persist(patient);

        procedure = new ProceduresEntity();
        procedure.setCode(1);
        procedure.setName("MRI Scan");
        procedure.setCost(500.0);
        entityManager.persist(procedure);

        RoomEntity room = new RoomEntity();
        room.setRoomNumber(202);
        entityManager.persist(room);

        stay = new StayEntity(10, patient, room,
                LocalDateTime.of(2024, 2, 1, 8, 0), LocalDateTime.of(2024, 2, 5, 12, 0));
        entityManager.persist(stay);

        physician = new PhysicianEntity();
        physician.setEmployeeId(501);
        physician.setName("Dr. House");
        entityManager.persist(physician);

        nurse = new NurseEntity();
        nurse.setEmployeeId(301);
        nurse.setName("Nurse Joy");
        entityManager.persist(nurse);

        entityManager.flush();

        undergoesId = new UndergoesId(patient.getSsn(), procedure.getCode(),
                stay.getStayId(), LocalDateTime.of(2024, 2, 2, 10, 0));

        UndergoesEntity undergoes = new UndergoesEntity(undergoesId, patient,
                procedure, stay, physician, nurse);
        undergoesRepository.save(undergoes);
    }

    @Test
    void testSaveUndergoes() {
        UndergoesId newId = new UndergoesId(patient.getSsn(), procedure.getCode(),
                stay.getStayId(), LocalDateTime.of(2024, 2, 3, 14, 0));
        UndergoesEntity newUndergoes = new UndergoesEntity(newId, patient,
                procedure, stay, physician, nurse);

        UndergoesEntity saved = undergoesRepository.save(newUndergoes);
        assertThat(saved).isNotNull();
        assertThat(saved.getUndergoesId()).isEqualTo(newId);
    }

    @Test
    void testFindById_Found() {
        Optional<UndergoesEntity> found = undergoesRepository.findById(undergoesId);
        assertThat(found).isPresent();
        assertThat(found.get().getProcedures().getName()).isEqualTo("MRI Scan");
    }

    @Test
    void testFindById_NotFound() {
        UndergoesId fakeId = new UndergoesId(9999, 9999, 9999, LocalDateTime.now());
        Optional<UndergoesEntity> found = undergoesRepository.findById(fakeId);
        assertThat(found).isEmpty();
    }

    @Test
    void testFindAll() {
        List<UndergoesEntity> all = undergoesRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void testDeleteById() {
        undergoesRepository.deleteById(undergoesId);
        assertThat(undergoesRepository.findById(undergoesId)).isEmpty();
    }

    @Test
    void testCountUndergoes() {
        assertThat(undergoesRepository.count()).isEqualTo(1);
    }

    @Test
    void testExistsById_True() {
        assertThat(undergoesRepository.existsById(undergoesId)).isTrue();
    }

    @Test
    void testExistsById_False() {
        UndergoesId fakeId = new UndergoesId(0, 0, 0, LocalDateTime.now());
        assertThat(undergoesRepository.existsById(fakeId)).isFalse();
    }

    @Test
    void testUpdateAssistingNurse() {
        UndergoesEntity u = undergoesRepository.findById(undergoesId).get();

        NurseEntity newNurse = new NurseEntity();
        newNurse.setEmployeeId(302);
        newNurse.setName("Nurse Ratched");
        entityManager.persist(newNurse);
        entityManager.flush();

        u.setAssistingNurse(newNurse);
        undergoesRepository.save(u);

        UndergoesEntity updated = undergoesRepository.findById(undergoesId).get();
        assertThat(updated.getAssistingNurse().getEmployeeId()).isEqualTo(302);
    }
}
