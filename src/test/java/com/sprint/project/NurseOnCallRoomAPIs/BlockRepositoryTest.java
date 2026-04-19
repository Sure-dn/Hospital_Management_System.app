package com.sprint.project.NurseOnCallRoomAPIs;

import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.repository.BlockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    // =========================
    // CREATE (Used in all tests)
    // =========================
    private BlockEntity createBlock(Integer floor, Integer code, String name) {
        BlockEntity block = new BlockEntity();

        // ✅ Set directly (NO BlockId)
        block.setBlockFloor(floor);
        block.setBlockCode(code);
        block.setName(name);

        return blockRepository.save(block);
    }

    // =========================
    // READ + DERIVED QUERY
    // =========================
    @Test
    @DisplayName("Test findByBlockFloor")
    void testFindByBlockFloor() {
        createBlock(1, 101, "ICU Block");
        createBlock(2, 201, "Ward Block");

        List<BlockEntity> blocks = blockRepository.findByBlockFloor(1);

        assertThat(blocks).hasSize(1);
    }

    // =========================
    // READ + CUSTOM QUERY
    // =========================
    @Test
    @DisplayName("Test searchByName")
    void testSearchByName() {
        createBlock(1, 101, "ICU Block");

        List<BlockEntity> blocks = blockRepository.searchByName("icu");

        assertThat(blocks).hasSize(1);
    }

    // =========================
    // READ + EXISTS
    // =========================
    @Test
    @DisplayName("Test existsByBlockFloorAndBlockCode")
    void testExists() {
        createBlock(1, 101, "ICU Block");

        boolean exists = blockRepository
                .existsByBlockFloorAndBlockCode(1, 101);

        assertThat(exists).isTrue();
    }

    // =========================
    // READ + FIND BY COMPOSITE KEY
    // =========================
    @Test
    @DisplayName("Test findByBlockFloorAndBlockCode")
    void testFindByFloorAndCode() {
        createBlock(1, 101, "ICU Block");

        Optional<BlockEntity> block =
                blockRepository.findByBlockFloorAndBlockCode(1, 101);

        assertThat(block).isPresent();
    }


}