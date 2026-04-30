package com.sprint.project.nurseoncallroom;

import com.sprint.project.nurseoncallroom.dto.request.NurseRequestDTO;
import com.sprint.project.nurseoncallroom.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.*;
import com.sprint.project.nurseoncallroom.service.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class ServiceTest {

    @Autowired
    private NurseService nurseService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private OnCallService onCallService;

    // ---------------- NURSE ----------------

    @Test
    void tc01_createNurse_success() {
        NurseRequestDTO req = new NurseRequestDTO();
        req.setEmployeeId(1);
        req.setName("John");

        NurseResponseDTO res = nurseService.createNurse(req);

        assertNotNull(res);
        assertEquals("John", res.getName());
    }

    @Test
    void tc02_createNurse_duplicate_throwsException() {
        NurseRequestDTO req = new NurseRequestDTO();
        req.setEmployeeId(2);
        req.setName("Sam");

        nurseService.createNurse(req);

        assertThrows(Exception.class, () -> {
            nurseService.createNurse(req);
        });
    }

    @Test
    void tc03_getNurseById_success() {
        NurseRequestDTO req = new NurseRequestDTO();
        req.setEmployeeId(3);
        req.setName("Jane");

        nurseService.createNurse(req);

        NurseResponseDTO res = nurseService.getNurseById(3);

        assertEquals("Jane", res.getName());
    }

    @Test
    void tc04_getNurseById_notFound() {
        assertThrows(Exception.class, () -> {
            nurseService.getNurseById(999);
        });
    }

    @Test
    void tc05_updateNurse_success() {
        NurseRequestDTO req = new NurseRequestDTO();
        req.setEmployeeId(4);
        req.setName("Old");

        nurseService.createNurse(req);

        NurseRequestDTO update = new NurseRequestDTO();
        update.setName("Updated");

        NurseResponseDTO res = nurseService.updateNurse(4, update);

        assertEquals("Updated", res.getName());
    }

    // ---------------- BLOCK ----------------

    @Test
    void tc06_getAllBlocks() {
        List<BlockResponseDTO> list = blockService.getAllBlocks();
        assertNotNull(list);
    }

    @Test
    void tc07_getRoomsForBlock_success() {
        List<RoomResponseDTO> list = blockService.getRoomsForBlock(1, 1);
        assertNotNull(list);
    }

    @Test
    void tc08_getRoomsForBlock_notFound() {
        assertThrows(Exception.class, () -> {
            blockService.getRoomsForBlock(999, 999);
        });
    }

    // ---------------- ROOM ----------------

    @Test
    void tc09_getAllRooms() {
        List<RoomResponseDTO> list = roomService.getAllRooms();
        assertNotNull(list);
    }

    @Test
    void tc10_getRoomByNumber() {
        RoomResponseDTO room = roomService.getRoomByNumber(101);
        assertNotNull(room);
    }

    @Test
    void tc11_getRoomByNumber_notFound() {
        assertThrows(Exception.class, () -> {
            roomService.getRoomByNumber(999);
        });
    }

    // ---------------- ON CALL ----------------

    @Test
    void tc12_assignOnCall_success() {
        NurseRequestDTO nurse = new NurseRequestDTO();
        nurse.setEmployeeId(10);
        nurse.setName("OC Nurse");
        nurse.setPosition("Staff");
        nurse.setSsn(123456789);
        nurse.setRegistered(true);

        nurseService.createNurse(nurse);

        OnCallRequestDTO req = new OnCallRequestDTO();
        req.setBlockFloor(1);
        req.setBlockCode(1);
        req.setOnCallStart(LocalDateTime.now());
        req.setOnCallEnd(LocalDateTime.now().plusHours(2));

        OnCallResponseDTO res = onCallService.assignOnCall(10, req);

        assertNotNull(res);
        assertEquals(10, res.getNurseEmployeeId());
    }

    @Test
    void tc13_assignOnCall_nurseNotFound() {
        OnCallRequestDTO req = new OnCallRequestDTO();
        req.setBlockFloor(1);
        req.setBlockCode(1);

        assertThrows(Exception.class, () -> {
            onCallService.assignOnCall(999, req);
        });
    }

    @Test
    void tc14_getOnCallByNurse() {
        NurseRequestDTO nurse = new NurseRequestDTO();
        nurse.setEmployeeId(20);
        nurse.setName("Test Nurse");
        nurse.setPosition("Staff");
        nurse.setSsn(222334444);
        nurse.setRegistered(true);

        nurseService.createNurse(nurse);

        OnCallRequestDTO req = new OnCallRequestDTO();
        req.setBlockFloor(1);
        req.setBlockCode(1);
        req.setOnCallStart(LocalDateTime.now());
        req.setOnCallEnd(LocalDateTime.now().plusHours(3));

        onCallService.assignOnCall(20, req);

        List<OnCallResponseDTO> list = onCallService.getOnCallByNurse(20);

        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    void tc15_deleteOnCall() {
        NurseRequestDTO nurse = new NurseRequestDTO();
        nurse.setEmployeeId(30);
        nurse.setName("Delete Nurse");
        nurse.setPosition("Staff");
        nurse.setSsn(555667777);
        nurse.setRegistered(true);

        nurseService.createNurse(nurse);

        OnCallRequestDTO req = new OnCallRequestDTO();
        req.setBlockFloor(1);
        req.setBlockCode(1);
        req.setOnCallStart(LocalDateTime.now());
        req.setOnCallEnd(LocalDateTime.now().plusHours(2));

        onCallService.assignOnCall(30, req);

        // delete
        onCallService.deleteOnCall(30);

        List<OnCallResponseDTO> list = onCallService.getOnCallByNurse(30);

        assertTrue(list.isEmpty());
    }
}



