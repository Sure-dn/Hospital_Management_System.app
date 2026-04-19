package com.sprint.project.physicianDepartmentManagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sprint.project.physicianDepartmentManagement.entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.repository.PhysicianRepository;

@DataJpaTest
class PhysicianRepositoryTest {


    @Autowired
    private PhysicianRepository physicianRepository;

    @Test
    void testCreatePhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1001);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1111);

        PhysicianEntity saved = physicianRepository.save(p);

        assertThat(saved.getEmployeeId()).isNotNull();
    }

    @Test
    void testReadPhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1002);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1112);

        physicianRepository.save(p);

        Optional<PhysicianEntity> found =
                physicianRepository.findById(1002);

        assertThat(found).isPresent();
    }

    @Test
    void testUpdatePhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1003);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1113);

        PhysicianEntity saved = physicianRepository.save(p);

        saved.setPosition("Neurologist");
        physicianRepository.save(saved);

        assertThat(physicianRepository.findById(1003)
                .get()
                .getPosition())
                .isEqualTo("Neurologist");
    }

    @Test
    void testDeletePhysician() {

        PhysicianEntity p = new PhysicianEntity();
        p.setEmployeeId(1004);
        p.setName("John");
        p.setPosition("Cardiologist");
        p.setSsn(1114);

        physicianRepository.save(p);

        physicianRepository.deleteById(1004);

        assertThat(physicianRepository.findById(1004)).isEmpty();
    }
}