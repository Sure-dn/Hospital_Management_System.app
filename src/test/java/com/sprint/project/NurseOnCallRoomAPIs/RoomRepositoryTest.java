package com.sprint.project.NurseOnCallRoomAPIs;



import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.BlockRepository;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BlockRepository blockRepository;

    // =========================
    // CREATE (Helper methods)
    // =========================

    private BlockEntity createBlock(Integer floor, Integer code, String name) {
        BlockEntity block = new BlockEntity();
        block.setBlockFloor(floor);
        block.setBlockCode(code);
        block.setName(name);
        return blockRepository.save(block);
    }

    private RoomEntity createRoom(BlockEntity block, String type, Boolean unavailable) {
        RoomEntity room = new RoomEntity();
        room.setBlock(block); // relationship
        room.setType(type);
        room.setUnavailable(unavailable);
        return roomRepository.save(room);
    }

    // =========================
    // READ + DERIVED QUERY
    // findByBlock()
    // =========================
    @Test
    @DisplayName("Test findByBlock")
    void testFindByBlock() {
        BlockEntity block = createBlock(1, 101, "ICU Block");

        createRoom(block, "ICU", false);

        List<RoomEntity> rooms = roomRepository.findByBlock(block);

        assertThat(rooms).hasSize(1);
    }

    // =========================
    // READ + DERIVED QUERY
    // findByUnavailable()
    // =========================
    @Test
    @DisplayName("Test findByUnavailable")
    void testFindByUnavailable() {
        BlockEntity block = createBlock(1, 101, "ICU Block");

        createRoom(block, "ICU", true);
        createRoom(block, "General", false);

        List<RoomEntity> rooms = roomRepository.findByUnavailable(true);

        assertThat(rooms).hasSize(1);
    }

    // =========================
    // READ + DERIVED QUERY
    // findByType()
    // =========================
    @Test
    @DisplayName("Test findByType")
    void testFindByType() {
        BlockEntity block = createBlock(1, 101, "ICU Block");

        createRoom(block, "ICU", false);

        List<RoomEntity> rooms = roomRepository.findByType("ICU");

        assertThat(rooms).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY (@Query)
    // findByBlockFloorAndBlockCodeAndUnavailable()
    // =========================
    @Test
    @DisplayName("Test findByBlockFloorAndBlockCodeAndUnavailable")
    void testFindByBlockAndAvailability() {
        BlockEntity block = createBlock(1, 101, "ICU Block");

        createRoom(block, "ICU", true);

        List<RoomEntity> rooms =
                roomRepository.findByBlockFloorAndBlockCodeAndUnavailable(1, 101, true);

        assertThat(rooms).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY
    // findByBlockAndType()
    // =========================
    @Test
    @DisplayName("Test findByBlockAndType")
    void testFindByBlockAndType() {
        BlockEntity block = createBlock(1, 101, "ICU Block");

        createRoom(block, "ICU", false);

        List<RoomEntity> rooms =
                roomRepository.findByBlockAndType(1, 101, "ICU");

        assertThat(rooms).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY
    // findRoomsByBlock()
    // =========================
    @Test
    @DisplayName("Test findRoomsByBlock")
    void testFindRoomsByBlock() {
        BlockEntity block = createBlock(1, 101, "ICU Block");

        createRoom(block, "ICU", false);

        List<RoomEntity> rooms =
                roomRepository.findRoomsByBlock(1, 101);

        assertThat(rooms).hasSize(1);
    }
}
