package com.sprint.project.treatment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.sprint.project.nurseoncallroom.entity.BlockEntity;
import com.sprint.project.nurseoncallroom.entity.NurseEntity;
import com.sprint.project.nurseoncallroom.entity.RoomEntity;
import com.sprint.project.patientAppointment.entity.PatientEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.treatmentprostayy.entities.ProceduresEntity;
import com.sprint.project.treatmentprostayy.entities.StayEntity;
import com.sprint.project.treatmentprostayy.entities.UndergoesEntity;
import com.sprint.project.treatmentprostayy.entities.UndergoesId;
import com.sprint.project.treatmentprostayy.repositories.UndergoesRepository;

@DataJpaTest
@EntityScan(basePackages = "com.sprint.project")
@EnableJpaRepositories(basePackages = "com.sprint.project")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

        // ✅ Patient
    	patient = new PatientEntity();
    	patient.setSsn(1);
    	patient.setName("Test Patient");
    	patient.setAddress("Chennai");        
    	patient.setPhone("9876543210");       
    	patient.setInsuranceId(123);          
    	patient.setPcp(101);               

    	entityManager.persist(patient);

        // ✅ Procedure
        procedure = new ProceduresEntity();
        procedure.setCode(1);
        procedure.setName("Test Procedure");
        procedure.setCost(100.0);
        entityManager.persist(procedure);

     // ✅ Create Block
        BlockEntity block = new BlockEntity();
        block.setBlockFloor(1);
        block.setBlockCode(1);

        entityManager.persist(block);

        // ✅ Room
        RoomEntity room = new RoomEntity();
        room.setRoomNumber(101);
        room.setUnavailable(true);
        room.setBlock(block);   // ✅ CORRECT

        entityManager.persist(room);
        // ✅ Stay
        stay = new StayEntity(
                1,
                patient,
                room,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );
        entityManager.persist(stay);

        // ✅ Physician
        physician = new PhysicianEntity();
        physician.setEmployeeId(1);
        physician.setName("Doctor");
        physician.setPosition("High dept");
        physician.setSsn(1); 
        entityManager.persist(physician);

        // ✅ Nurse
        nurse = new NurseEntity();
        nurse.setEmployeeId(1);
        nurse.setName("Nurse");
        nurse.setPosition("nurseHigh");
        nurse.setRegistered(true);   // ✅ REQUIRED
        nurse.setSsn(1);
        entityManager.persist(nurse);

        entityManager.flush();

        // ✅ Composite Key
        undergoesId = new UndergoesId(
                patient.getSsn(),
                procedure.getCode(),
                stay.getStayId(),
                LocalDateTime.now()
        );

        // ✅ Undergoes
        UndergoesEntity undergoes = new UndergoesEntity(
                undergoesId,
                patient,
                procedure,
                stay,
                physician,
                nurse
        );

        entityManager.persist(undergoes);
        entityManager.flush();
    }

    @Test
    void testFindById() {
        Optional<UndergoesEntity> result = undergoesRepository.findById(undergoesId);
        assertThat(result).isPresent();
    }

    @Test
    void testSave() {
        UndergoesId newId = new UndergoesId(
                patient.getSsn(),
                procedure.getCode(),
                stay.getStayId(),
                LocalDateTime.now().plusHours(1)
        );

        UndergoesEntity newEntity = new UndergoesEntity(
                newId,
                patient,
                procedure,
                stay,
                physician,
                nurse
        );

        UndergoesEntity saved = undergoesRepository.save(newEntity);
        assertThat(saved).isNotNull();
    }

    @Test
    void testDelete() {
        undergoesRepository.deleteById(undergoesId);
        assertThat(undergoesRepository.findById(undergoesId)).isEmpty();
    }
}