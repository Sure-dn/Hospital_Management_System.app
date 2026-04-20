package com.sprint.project.physicianDepartmentManagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.entity.TrainedInEntity;
import com.sprint.project.physicianDepartmentManagement.entity.TrainedInId;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.repository.TrainedInRepository;
import com.sprint.project.Treatment.Entity.ProceduresEntity;
import com.sprint.project.Treatment.Repository.ProceduresRepository;


@SpringBootTest
@Transactional
public class TrainedIntRepositoryTest {
	@Autowired
    private TrainedInRepository trainedRepo;

    @Autowired
    private PhysicianRepository physicianRepo;

    @Autowired
    private ProceduresRepository procedureRepo;

    //  CREATE
    @Test
    void testCreateTrainedIn() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1111);
        physicianRepo.save(p);

        ProceduresEntity pr = new ProceduresEntity();
        pr.setCode(1); // assuming PK is code
        pr.setName("Bypass Surgery");
        procedureRepo.save(pr);

        TrainedInEntity t = new TrainedInEntity();
        t.setTrainedInId(new TrainedInId(1, 1));
        t.setPhysician(p);
        t.setTreatment(pr);
        t.setCertificationExpiry(LocalDate.of(2026, 12, 31));

        TrainedInEntity saved = trainedRepo.save(t);

        assertThat(saved.getTrainedInId()).isNotNull();
    }

    //  READ
    @Test
    void testReadTrainedIn() {

        TrainedInId id = new TrainedInId(2, 2);

        Optional<TrainedInEntity> found =
                trainedRepo.findById(id);

        assertThat(found).isNotNull();
    }

    //  UPDATE
    @Test
    void testUpdateTrainedIn() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(3);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1113);
        physicianRepo.save(p);

        ProceduresEntity pr = new ProceduresEntity();
        pr.setCode(3);
        pr.setName("Heart Surgery");
        procedureRepo.save(pr);

        TrainedInEntity t = new TrainedInEntity();
        t.setTrainedInId(new TrainedInId(3, 3));
        t.setPhysician(p);
        t.setTreatment(pr);
        t.setCertificationExpiry(LocalDate.of(2026, 12, 31));

        TrainedInEntity saved = trainedRepo.save(t);

        saved.setCertificationExpiry(LocalDate.of(2027, 12, 31));
        trainedRepo.save(saved);

        assertThat(trainedRepo.findById(new TrainedInId(3, 3))
                .get()
                .getCertificationExpiry())
                .isEqualTo(LocalDate.of(2027, 12, 31));
    }

    // DELETE
    @Test
    void testDeleteTrainedIn() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(4);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1114);
        physicianRepo.save(p);

        ProceduresEntity pr = new ProceduresEntity();
        pr.setCode(4);
        pr.setName("Brain Surgery");
        procedureRepo.save(pr);

        TrainedInEntity t = new TrainedInEntity();
        t.setTrainedInId(new TrainedInId(4, 4));
        t.setPhysician(p);
        t.setTreatment(pr);
        t.setCertificationExpiry(LocalDate.of(2026, 12, 31));

        trainedRepo.save(t);

        trainedRepo.deleteById(new TrainedInId(4, 4));

        assertThat(trainedRepo.findById(new TrainedInId(4, 4)))
                .isEmpty();
    }

}
