package com.sprint.project.physicianDepartmentManagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;

@SpringBootTest
@Transactional
class PhysicianRepositoryTest {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Test
    void testCreatePhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1111);

        PhysicianEntity saved = physicianRepository.save(p);

        assertThat(saved.getEmployeeId()).isEqualTo(1);
    }

    @Test
    void testReadPhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(2);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1112);

        physicianRepository.save(p);

        Optional<PhysicianEntity> found =
                physicianRepository.findById(2);

        assertThat(found).isPresent();
    }

    @Test
    void testUpdatePhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(3);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1113);

        PhysicianEntity saved = physicianRepository.save(p);

        saved.setPosition("Neurologist");
        physicianRepository.save(saved);

        assertThat(physicianRepository.findById(3)
                .get()
                .getPosition())
                .isEqualTo("Neurologist");
    }

    @Test
    void testDeletePhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(4);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1114);

        physicianRepository.save(p);

        physicianRepository.deleteById(4);

        assertThat(physicianRepository.findById(4)).isEmpty();
    }
}