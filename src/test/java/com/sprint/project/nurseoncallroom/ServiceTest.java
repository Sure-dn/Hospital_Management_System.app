package com.sprint.project.nurseoncallroom;

import com.sprint.project.nurseoncallroom.dto.request.NurseRequestDTO;
import com.sprint.project.nurseoncallroom.dto.request.OnCallRequestDTO;
import com.sprint.project.nurseoncallroom.dto.response.*;
import com.sprint.project.nurseoncallroom.entity.*;
import com.sprint.project.nurseoncallroom.exception.*;
import com.sprint.project.nurseoncallroom.repository.*;
import com.sprint.project.nurseoncallroom.service.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired private NurseRepository nurseRepository;
    @Autowired private BlockRepository blockRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private OnCallRepository onCallRepository;

    @Autowired private NurseService nurseService;
    @Autowired private BlockService blockService;
    @Autowired private RoomService roomService;
    @Autowired private OnCallService onCallService;

    @PersistenceContext
    private EntityManager em;

    // ───────────────── CLEAN DB ─────────────────
    @BeforeEach
    void cleanDB() {
        onCallRepository.deleteAll();
        roomRepository.deleteAll();
        blockRepository.deleteAll();
        nurseRepository.deleteAll();
        em.flush();
        em.clear();
    }

    // ───────────────── HELPERS ─────────────────
    private NurseRequestDTO nurseDTO(int id) {
        NurseRequestDTO dto = new NurseRequestDTO();
        dto.setEmployeeId(id);
        dto.setName("Nurse-" + id);
        dto.setPosition("Staff Nurse");
        dto.setRegistered(true);
        dto.setSsn(900000 + id);
        return dto;
    }

    private NurseEntity persistNurse(int id) {
        NurseEntity n = new NurseEntity();
        n.setEmployeeId(id);
        n.setName("Nurse-" + id);
        n.setPosition("Staff Nurse");
        n.setRegistered(true);
        n.setSsn(900000 + id);
        NurseEntity saved = nurseRepository.save(n);
        em.flush(); em.clear();
        return saved;
    }

    private BlockEntity persistBlock(int floor, int code) {
        BlockEntity saved = blockRepository.save(
                new BlockEntity(floor, code, "Block-" + floor + "-" + code));
        em.flush(); em.clear();
        return saved;
    }

    private RoomEntity persistRoom(BlockEntity block) {
        BlockEntity managed = em.find(BlockEntity.class,
                new BlockId(block.getBlockFloor(), block.getBlockCode()));

        RoomEntity r = new RoomEntity();
        r.setRoomType("GENERAL");
        r.setUnavailable(false);
        r.setBlock(managed != null ? managed : block);

        RoomEntity saved = roomRepository.save(r);
        em.flush(); em.clear();
        return saved;
    }

    private OnCallRequestDTO onCallDTO(int floor, int code, int offset) {
        OnCallRequestDTO dto = new OnCallRequestDTO();
        dto.setBlockFloor(floor);
        dto.setBlockCode(code);
        dto.setOnCallStart(LocalDateTime.now().plusHours(offset));
        dto.setOnCallEnd(LocalDateTime.now().plusHours(offset + 8));
        return dto;
    }

    // ───────────────── TC01-05 NURSE ─────────────────
    @Test
    void tc01_createNurse_success() {
        NurseResponseDTO res = nurseService.createNurse(nurseDTO(1));
        assertEquals(1, res.getEmployeeId());
    }

    @Test
    void tc02_createNurse_duplicate_throwsException() {
        nurseService.createNurse(nurseDTO(2));
        assertThrows(BlockDuplicateResourceException.class,
                () -> nurseService.createNurse(nurseDTO(2)));
    }

    @Test
    void tc03_getNurseById_success() {
        nurseService.createNurse(nurseDTO(3));
        assertEquals(3, nurseService.getNurseById(3).getEmployeeId());
    }

    @Test
    void tc04_getNurseById_notFound() {
        assertThrows(OnCallNotFoundException.class,
                () -> nurseService.getNurseById(999));
    }

    @Test
    void tc05_updateNurse_success() {
        nurseService.createNurse(nurseDTO(5));
        NurseRequestDTO dto = nurseDTO(5);
        dto.setName("Updated");
        assertEquals("Updated", nurseService.updateNurse(5, dto).getName());
    }

    // ───────────────── TC06-08 BLOCK ─────────────────
    @Test
    void tc06_getAllBlocks() {
        persistBlock(1,1);
        persistBlock(1,2);

        em.flush(); em.clear();

        assertThat(blockService.getAllBlocks()).isNotEmpty();
    }

    @Test
    void tc07_getRoomsForBlock_success() {
        BlockEntity b = persistBlock(2,1);
        persistRoom(b);
        persistRoom(b);

        // 🔥 FIX
        em.flush(); em.clear();

        assertThat(blockService.getRoomsForBlock(2,1)).hasSize(2);
    }

    @Test
    void tc08_getRoomsForBlock_notFound() {
        assertThrows(OnCallNotFoundException.class,
                () -> blockService.getRoomsForBlock(9,9));
    }

    // ───────────────── TC09-11 ROOM ─────────────────
    @Test
    void tc09_getAllRooms() {
        BlockEntity b = persistBlock(3,1);
        persistRoom(b);

        // 🔥 FIX
        em.flush(); em.clear();

        assertThat(roomService.getAllRooms()).isNotEmpty();
    }

    @Test
    void tc10_getRoomByNumber() {
        BlockEntity b = persistBlock(4,1);
        RoomEntity r = persistRoom(b);
        Integer id = r.getRoomNumber();

        // 🔥 FIX
        em.flush(); em.clear();

        assertEquals(id, roomService.getRoomByNumber(id).getRoomNumber());
    }

    @Test
    void tc11_getRoomByNumber_notFound() {
        assertThrows(OnCallNotFoundException.class,
                () -> roomService.getRoomByNumber(9999));
    }

    // ───────────────── TC12-15 ONCALL ─────────────────
    @Test
    void tc12_assignOnCall_success() {
        persistNurse(10);
        persistBlock(5,5);

        OnCallResponseDTO res =
                onCallService.assignOnCall(10, onCallDTO(5,5,1));

        assertEquals(10, res.getNurseEmployeeId());
    }

    @Test
    void tc13_assignOnCall_nurseNotFound() {
        assertThrows(OnCallNotFoundException.class,
                () -> onCallService.assignOnCall(999, onCallDTO(5,5,1)));
    }

    @Test
    void tc14_getOnCallByNurse() {
        persistNurse(20);
        persistBlock(7,7);

        onCallService.assignOnCall(20, onCallDTO(7,7,2));

        em.flush(); em.clear();

        assertThat(onCallService.getOnCallByNurse(20)).isNotEmpty();
    }

    @Test
    void tc15_deleteOnCall() {
        persistNurse(30);
        persistBlock(8,8);

        onCallService.assignOnCall(30, onCallDTO(8,8,3));

        em.flush(); em.clear();

        onCallService.deleteOnCall(30);

        em.flush(); em.clear();

        assertThat(onCallService.getOnCallByNurse(30)).isEmpty();
    }
}