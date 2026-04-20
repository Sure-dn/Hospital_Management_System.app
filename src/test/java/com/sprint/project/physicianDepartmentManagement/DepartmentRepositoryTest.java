package com.sprint.project.physicianDepartmentManagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.project.physicianDepartmentManagement.entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.repository.DepartmentRepository;


@SpringBootTest
@Transactional
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void testCreateDepartment() {

        DepartmentEntity d = new DepartmentEntity();
        d.setDepartmentId(1); // change if your PK name is different
        d.setName("Cardiology");

        DepartmentEntity saved = departmentRepository.save(d);

        assertThat(saved.getDepartmentId()).isEqualTo(1);
    }

    @Test
    void testReadDepartment() {

        DepartmentEntity d = new DepartmentEntity();
        d.setDepartmentId(2);
        d.setName("Neurology");

        departmentRepository.save(d);

        Optional<DepartmentEntity> found =
                departmentRepository.findById(2);

        assertThat(found).isPresent();
    }

    @Test
    void testUpdateDepartment() {

        DepartmentEntity d = new DepartmentEntity();
        d.setDepartmentId(3);
        d.setName("Ortho");

        DepartmentEntity saved = departmentRepository.save(d);

        saved.setName("Orthopedics");
        departmentRepository.save(saved);

        assertThat(departmentRepository.findById(3)
                .get()
                .getName())
                .isEqualTo("Orthopedics");
    }

    @Test
    void testDeleteDepartment() {

        DepartmentEntity d = new DepartmentEntity();
        d.setDepartmentId(4);
        d.setName("ENT");

        departmentRepository.save(d);

        departmentRepository.deleteById(4);

        assertThat(departmentRepository.findById(4)).isEmpty();
    }

}
