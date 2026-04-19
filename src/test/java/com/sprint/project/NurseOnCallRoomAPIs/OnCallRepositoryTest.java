package com.sprint.project.NurseOnCallRoomAPIs;



import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.OnCallEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.OnCallId;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;
import com.sprint.project.NurseOnCallRoomAPIs.repository.OnCallRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OnCallRepositoryTest {

    @Autowired
    private OnCallRepository onCallRepository;

    @Autowired
    private NurseRepository nurseRepository;

    // =========================
    // CREATE (Helper methods)
    // =========================

    private NurseEntity createNurse() {
        NurseEntity nurse = new NurseEntity();
        nurse.setName("Alice");
        nurse.setPosition("ICU");
        nurse.setRegistered(true);
        nurse.setSsn(101);
        return nurseRepository.save(nurse);
    }

    private OnCallEntity createOnCall(NurseEntity nurse,
                                      Integer floor,
                                      Integer code,
                                      LocalDateTime start,
                                      LocalDateTime end) {

        OnCallEntity onCall = new OnCallEntity();

        onCall.setNurse(nurse);
        onCall.setBlockFloor(floor);
        onCall.setBlockCode(code);
        onCall.setOnCallStart(start);
        onCall.setOnCallEnd(end);

        return onCallRepository.save(onCall);
    }

    // =========================
    // READ + DERIVED QUERY
    // findByNurse()
    // =========================
    @Test
    @DisplayName("Test findByNurse")
    void testFindByNurse() {
        NurseEntity nurse = createNurse();

        createOnCall(nurse, 1, 101,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));

        List<OnCallEntity> shifts = onCallRepository.findByNurse(nurse);

        assertThat(shifts).hasSize(1);
    }

    // =========================
    // READ + DERIVED QUERY
    // findByBlockFloorAndBlockCode()
    // =========================
    @Test
    @DisplayName("Test findByBlockFloorAndBlockCode")
    void testFindByBlock() {
        NurseEntity nurse = createNurse();

        createOnCall(nurse, 1, 101,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));

        List<OnCallEntity> shifts =
                onCallRepository.findByBlockFloorAndBlockCode(1, 101);

        assertThat(shifts).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY
    // findShiftsWithinWindow()
    // =========================
    @Test
    @DisplayName("Test findShiftsWithinWindow")
    void testFindShiftsWithinWindow() {
        NurseEntity nurse = createNurse();

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);

        createOnCall(nurse, 1, 101, start, end);

        List<OnCallEntity> shifts =
                onCallRepository.findShiftsWithinWindow(
                        start.minusHours(1),
                        end.plusHours(1)
                );

        assertThat(shifts).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY
    // findCurrentlyOnCall()
    // =========================
    @Test
    @DisplayName("Test findCurrentlyOnCall")
    void testFindCurrentlyOnCall() {
        NurseEntity nurse = createNurse();

        LocalDateTime now = LocalDateTime.now();

        createOnCall(nurse, 1, 101,
                now.minusHours(1),
                now.plusHours(1));

        List<OnCallEntity> shifts =
                onCallRepository.findCurrentlyOnCall(now);

        assertThat(shifts).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY
    // existsOverlappingShift()
    // =========================
    @Test
    @DisplayName("Test existsOverlappingShift")
    void testOverlappingShift() {
        NurseEntity nurse = createNurse();

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);

        createOnCall(nurse, 1, 101, start, end);

        boolean exists = onCallRepository.existsOverlappingShift(
                nurse,
                1,
                101,
                start.plusMinutes(30),
                end.plusHours(1)
        );

        assertThat(exists).isTrue();
    }

    // =========================
    // DELETE (CUSTOM - @Modifying)
    // deleteByNurse()
    // =========================
    @Test
    @Transactional
    @DisplayName("Test deleteByNurse")
    void testDeleteByNurse() {
        NurseEntity nurse = createNurse();

        createOnCall(nurse, 1, 101,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));

        onCallRepository.deleteByNurse(nurse);

        List<OnCallEntity> shifts = onCallRepository.findByNurse(nurse);

        assertThat(shifts).isEmpty();
    }


}
