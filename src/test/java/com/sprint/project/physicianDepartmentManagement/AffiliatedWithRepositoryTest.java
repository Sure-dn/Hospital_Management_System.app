package com.sprint.project.physicianDepartmentManagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.project.physicianDepartmentManagement.entity.AffiliatedId;
import com.sprint.project.physicianDepartmentManagement.entity.AffiliatedWithEntity;
import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.AffiliatedWithRepository;
import com.sprint.project.physicianDepartmentManagement.repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;


@SpringBootTest
@Transactional
public class AffiliatedWithRepositoryTest {
	 @Autowired
	    private AffiliatedWithRepository affiliatedRepo;

	    @Autowired
	    private PhysicianRepository physicianRepo;

	    @Autowired
	    private DepartmentRepository departmentRepo;

	    // CREATE
	    @Test
	    void testCreateAffiliation() {

	        PhysicianEntity p = new PhysicianEntity();
	        p.setEmployeeId(1);
	        p.setName("John");
	        p.setPosition("Cardiologist");
	        p.setSsn(1001);
	        physicianRepo.save(p);

	        DepartmentEntity d = new DepartmentEntity();
	        d.setDepartmentId(1); // adjust if needed
	        d.setName("Cardiology");
	        departmentRepo.save(d);

	        AffiliatedWithEntity a = new AffiliatedWithEntity();

	        a.setAffiliatedId(new AffiliatedId(1, 1));
	        a.setPhysician(p);
	        a.setDepartment(d);
	        a.setPrimaryAffiliation(true);

	        AffiliatedWithEntity saved = affiliatedRepo.save(a);

	        assertThat(saved.getAffiliatedId()).isNotNull();
	    }

	    //  READ
	    @Test
	    void testReadAffiliation() {

	        AffiliatedId id = new AffiliatedId(2, 2);

	        Optional<AffiliatedWithEntity> found =
	                affiliatedRepo.findById(id);

	        assertThat(found).isNotNull();
	    }

	    // UPDATE
	    @Test
	    void testUpdateAffiliation() {

	        PhysicianEntity p = new PhysicianEntity();
	        p.setEmployeeId(3);
	        p.setName("John");
	        p.setPosition("Cardiologist");
	        p.setSsn(1003);
	        physicianRepo.save(p);

	        DepartmentEntity d = new DepartmentEntity();
	        d.setDepartmentId(3);
	        d.setName("Neurology");
	        departmentRepo.save(d);

	        AffiliatedWithEntity a = new AffiliatedWithEntity();
	        a.setAffiliatedId(new AffiliatedId(3, 3));
	        a.setPhysician(p);
	        a.setDepartment(d);
	        a.setPrimaryAffiliation(true);

	        AffiliatedWithEntity saved = affiliatedRepo.save(a);

	        saved.setPrimaryAffiliation(false);
	        affiliatedRepo.save(saved);

	        assertThat(affiliatedRepo.findById(new AffiliatedId(3, 3))
	                .get()
	                .getPrimaryAffiliation())
	                .isFalse();
	    }

	    //  DELETE
	    @Test
	    void testDeleteAffiliation() {

	        PhysicianEntity p = new PhysicianEntity();
	        p.setEmployeeId(4);
	        p.setName("John");
	        p.setPosition("Cardiologist");
	        p.setSsn(1004);
	        physicianRepo.save(p);

	        DepartmentEntity d = new DepartmentEntity();
	        d.setDepartmentId(4);
	        d.setName("ENT");
	        departmentRepo.save(d);

	        AffiliatedWithEntity a = new AffiliatedWithEntity();
	        a.setAffiliatedId(new AffiliatedId(4, 4));
	        a.setPhysician(p);
	        a.setDepartment(d);
	        a.setPrimaryAffiliation(true);

	        affiliatedRepo.save(a);

	        affiliatedRepo.deleteById(new AffiliatedId(4, 4));

	        assertThat(affiliatedRepo.findById(new AffiliatedId(4, 4)))
	                .isEmpty();
	    }

}
