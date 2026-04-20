package com.sprint.project.physicianDepartmentManagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional

public class DepartmentRepositoryTest {
	 @Autowired
	    private DepartmentRepository departmentRepository;

	    @Autowired
	    private PhysicianRepository physicianRepository;

	    //  CREATE
	    @Test
	    void testCreateDepartment() {

	        PhysicianEntity head = new PhysicianEntity();
	        head.setEmployeeId(100);
	        head.setName("Dr.Head");
	        head.setPosition("Senior");
	        head.setSsn(9000);

	        physicianRepository.save(head);

	        DepartmentEntity dept = new DepartmentEntity();
	        dept.setDepartmentId(1);
	        dept.setName("Cardiology");
	        dept.setHead(head);

	        DepartmentEntity saved = departmentRepository.save(dept);

	        assertThat(saved.getDepartmentId()).isEqualTo(1);
	    }

	    //  READ
	    @Test
	    void testReadDepartment() {

	        PhysicianEntity head = new PhysicianEntity();
	        head.setEmployeeId(101);
	        head.setName("Dr.Head");
	        head.setPosition("Senior");
	        head.setSsn(9001);

	        physicianRepository.save(head);

	        DepartmentEntity dept = new DepartmentEntity();
	        dept.setDepartmentId(2);
	        dept.setName("Neurology");
	        dept.setHead(head);

	        departmentRepository.save(dept);

	        Optional<DepartmentEntity> found =
	                departmentRepository.findById(2);

	        assertThat(found).isPresent();
	    }

	    //  UPDATE
	    @Test
	    void testUpdateDepartment() {

	        PhysicianEntity head = new PhysicianEntity();
	        head.setEmployeeId(102);
	        head.setName("Dr.Head");
	        head.setPosition("Senior");
	        head.setSsn(9002);

	        physicianRepository.save(head);

	        DepartmentEntity dept = new DepartmentEntity();
	        dept.setDepartmentId(3);
	        dept.setName("Ortho");
	        dept.setHead(head);

	        departmentRepository.save(dept);

	        DepartmentEntity existing =
	                departmentRepository.findById(3).get();

	        existing.setName("Orthopedics");

	        departmentRepository.save(existing);

	        assertThat(departmentRepository.findById(3)
	                .get()
	                .getName())
	                .isEqualTo("Orthopedics");
	    }

	    //  DELETE
	    @Test
	    void testDeleteDepartment() {

	        PhysicianEntity head = new PhysicianEntity();
	        head.setEmployeeId(103);
	        head.setName("Dr.Head");
	        head.setPosition("Senior");
	        head.setSsn(9003);

	        physicianRepository.save(head);

	        DepartmentEntity dept = new DepartmentEntity();
	        dept.setDepartmentId(4);
	        dept.setName("ENT");
	        dept.setHead(head);

	        departmentRepository.save(dept);

	        departmentRepository.deleteById(4);

	        assertThat(departmentRepository.findById(4)).isEmpty();
	    }

}
