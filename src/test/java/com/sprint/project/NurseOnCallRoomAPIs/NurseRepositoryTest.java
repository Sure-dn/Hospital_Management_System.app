package com.sprint.project.NurseOnCallRoomAPIs;

import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NurseRepositoryTest {

    @Autowired
    private NurseRepository nurseRepository;

    // =========================
    // CREATE (Used in all tests)
    // =========================
    private NurseEntity createNurse(String name, String position, Boolean registered, Integer ssn) {
        NurseEntity nurse = new NurseEntity();
        nurse.setName(name);
        nurse.setPosition(position);
        nurse.setRegistered(registered);
        nurse.setSsn(ssn);
        return nurseRepository.save(nurse);
    }

    // =========================
    // READ + DERIVED QUERY
    // findByPosition()
    // =========================
    @Test
    @DisplayName("Test findByPosition")
    void testFindByPosition() {
        createNurse("Alice", "ICU", true, 101);
        createNurse("Bob", "Ward", false, 102);

        List<NurseEntity> nurses = nurseRepository.findByPosition("ICU");

        assertThat(nurses).hasSize(1);
        assertThat(nurses.get(0).getName()).isEqualTo("Alice");
    }

    // =========================
    // READ + DERIVED QUERY
    // findByRegistered()
    // =========================
    @Test
    @DisplayName("Test findByRegistered")
    void testFindByRegistered() {
        createNurse("Alice", "ICU", true, 101);
        createNurse("Bob", "Ward", false, 102);

        List<NurseEntity> nurses = nurseRepository.findByRegistered(true);

        assertThat(nurses).hasSize(1);
        assertThat(nurses.get(0).getRegistered()).isTrue();
    }

    // =========================
    // READ + DERIVED QUERY
    // findBySsn()
    // =========================
    @Test
    @DisplayName("Test findBySsn")
    void testFindBySsn() {
        createNurse("Alice", "ICU", true, 101);

        Optional<NurseEntity> nurse = nurseRepository.findBySsn(101);

        assertThat(nurse).isPresent();
        assertThat(nurse.get().getName()).isEqualTo("Alice");
    }

    // =========================
    // READ + CUSTOM QUERY (@Query)
    // searchByName()
    // =========================
    @Test
    @DisplayName("Test searchByName")
    void testSearchByName() {
        createNurse("Alice Johnson", "ICU", true, 101);
        createNurse("Bob", "Ward", false, 102);

        List<NurseEntity> nurses = nurseRepository.searchByName("alice");

        assertThat(nurses).hasSize(1);
        assertThat(nurses.get(0).getName()).contains("Alice");
    }

    // =========================
    // READ + CUSTOM QUERY (@Query)
    // findByPositionAndRegistered()
    // =========================
    @Test
    @DisplayName("Test findByPositionAndRegistered")
    void testFindByPositionAndRegistered() {
        createNurse("Alice", "ICU", true, 101);
        createNurse("Bob", "ICU", false, 102);

        List<NurseEntity> nurses =
                nurseRepository.findByPositionAndRegistered("ICU", true);

        assertThat(nurses).hasSize(1);
        assertThat(nurses.get(0).getRegistered()).isTrue();
    }

}