package com.sprint.project.physicianDepartmentManagement;



import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.physicianDepartmentManagement.entity.*;
import com.sprint.project.physicianDepartmentManagement.repository.*;
import com.sprint.project.treatmentprostayy.entities.ProceduresEntity;
import com.sprint.project.treatmentprostayy.repositories.ProceduresRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class TrainedInRepositoryTest {
	
	 @Autowired
	    private TrainedInRepository trainedInRepository;

	    @Autowired
	    private PhysicianRepository physicianRepository;

	    @Autowired
	    private ProceduresRepository proceduresRepository;

	    //  COMMON METHOD 
	    private TrainedInEntity createTrainedIn(int phyId, int procId, int ssn) {

	        // 1. Create Physician
	        PhysicianEntity p = new PhysicianEntity();
	        p.setEmployeeId(phyId);
	        p.setName("Doc" + phyId);
	        p.setPosition("Doctor");
	        p.setSsn(ssn);
	        physicianRepository.save(p);

	        // 2. Create Procedure
	        ProceduresEntity proc = new ProceduresEntity();
	        proc.setCode(procId);   // IMPORTANT: must match MapsId
	        proc.setName("Treatment" + procId);
	        proceduresRepository.save(proc);
	       
	        // 3. Composite ID
	        TrainedInId id = new TrainedInId(phyId, procId);

	        // 4. Create Entity
	        TrainedInEntity t = new TrainedInEntity();
	        t.setTrainedInId(id);
	        t.setPhysician(p);
	        t.setTreatment(proc);
	        t.setCertificationExpiry(LocalDate.now().plusDays(10));

	        return trainedInRepository.save(t);
	    }

	    //  CREATE
	    @Test
	    void testCreate() {

	        TrainedInEntity saved = createTrainedIn(501, 61, 9001);

	        assertThat(saved).isNotNull();
	        assertThat(saved.getTrainedInId()).isNotNull();
	    }

	    //  READ
	    @Test
	    void testRead() {

	        createTrainedIn(502, 62, 9002);

	        TrainedInId id = new TrainedInId(502, 62);

	        Optional<TrainedInEntity> found =
	                trainedInRepository.findById(id);

	        assertThat(found).isPresent();
	    }

	    //  UPDATE
	    @Test
	    void testUpdate() {

	        createTrainedIn(503, 63, 9003);

	        TrainedInId id = new TrainedInId(503, 63);

	        TrainedInEntity existing =
	                trainedInRepository.findById(id).get();

	        existing.setCertificationExpiry(LocalDate.now().plusDays(30));

	        trainedInRepository.save(existing);

	        assertThat(trainedInRepository.findById(id)
	                .get()
	                .getCertificationExpiry())
	                .isNotNull();
	    }

	    //  DELETE
	    @Test
	    void testDelete() {

	        createTrainedIn(504, 64, 9004);

	        TrainedInId id = new TrainedInId(504, 64);

	        trainedInRepository.deleteById(id);

	        assertThat(trainedInRepository.findById(id)).isEmpty();
	    }

}
