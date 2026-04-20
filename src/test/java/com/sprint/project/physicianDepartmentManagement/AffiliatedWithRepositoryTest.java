package com.sprint.project.physicianDepartmentManagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.physicianDepartmentManagement.entity.*;
import com.sprint.project.physicianDepartmentManagement.repository.*;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class AffiliatedWithRepositoryTest {
	  @Autowired
	    private AffiliatedWithRepository affiliatedWithRepository;

	    @Autowired
	    private PhysicianRepository physicianRepository;

	    @Autowired
	    private DepartmentRepository departmentRepository;

	    //  COMMON METHOD TO CREATE DATA 
	    private AffiliatedWithEntity createAffiliation(int phyId, int deptId, int ssn) {

	        // 1. Create Physician
	        PhysicianEntity p = new PhysicianEntity();
	        p.setEmployeeId(phyId);
	        p.setName("Doc" + phyId);
	        p.setPosition("Doctor");
	        p.setSsn(ssn);
	        physicianRepository.save(p);

	        // 2. Create Department
	        DepartmentEntity d = new DepartmentEntity();
	        d.setDepartmentId(deptId);
	        d.setName("Dept" + deptId);
	        d.setHead(p);
	        departmentRepository.save(d);

	        // 3. Create Composite ID
	        AffiliatedId id = new AffiliatedId(phyId, deptId);

	        // 4. Create Affiliation
	        AffiliatedWithEntity a = new AffiliatedWithEntity();
	        a.setAffiliatedId(id);
	        a.setPhysician(p);
	        a.setDepartment(d);
	        a.setPrimaryAffiliation(true);

	        return affiliatedWithRepository.save(a);
	    }

	    //  CREATE
	    @Test
	    void testCreate() {

	        AffiliatedWithEntity saved = createAffiliation(401, 31, 8001);

	        assertThat(saved).isNotNull();
	        assertThat(saved.getAffiliatedId()).isNotNull();
	    }

	    //  READ
	    @Test
	    void testRead() {

	        createAffiliation(402, 32, 8002);

	        AffiliatedId id = new AffiliatedId(402, 32);

	        Optional<AffiliatedWithEntity> found =
	                affiliatedWithRepository.findById(id);

	        assertThat(found).isPresent();
	    }

	    //  UPDATE
	    @Test
	    void testUpdate() {

	        createAffiliation(403, 33, 8003);

	        AffiliatedId id = new AffiliatedId(403, 33);

	        AffiliatedWithEntity existing =
	                affiliatedWithRepository.findById(id).get();

	        existing.setPrimaryAffiliation(false);

	        affiliatedWithRepository.save(existing);

	        assertThat(affiliatedWithRepository.findById(id)
	                .get()
	                .getPrimaryAffiliation())
	                .isFalse();
	    }

	    //  DELETE
	    @Test
	    void testDelete() {

	        createAffiliation(404, 34, 8004);

	        AffiliatedId id = new AffiliatedId(404, 34);

	        affiliatedWithRepository.deleteById(id);

	        assertThat(affiliatedWithRepository.findById(id)).isEmpty();
	    }
	

}
