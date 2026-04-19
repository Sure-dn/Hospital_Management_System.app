package com.sprint.project.NurseOnCallRoomAPIs;

import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RoomEntity and BlockEntity validations.
 */
class RoomAndBlockEntityValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ========================
    // RoomEntity Tests
    // ========================

    private RoomEntity validRoom() {
        BlockEntity block = new BlockEntity();
        block.setBlockFloor(1);
        block.setBlockCode(1);

        RoomEntity room = new RoomEntity();
        room.setRoomNumber(101);
        room.setRoomType("ICU");
        room.setUnavailable(false);
        room.setBlock(block);
        return room;
    }

    @Test
    @DisplayName("Valid RoomEntity should have no violations")
    void validRoom_noViolations() {
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(validRoom());
        assertTrue(violations.isEmpty());
    }










    @Test
    @DisplayName("Null unavailable flag should fail @NotNull")
    void nullUnavailable_shouldViolate() {
        RoomEntity room = validRoom();
        room.setUnavailable(null);
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("unavailable")));
    }



    // ========================
    // BlockEntity Tests
    // ========================

    private BlockEntity validBlock() {
        BlockEntity block = new BlockEntity();
        block.setBlockFloor(2);
        block.setBlockCode(3);
        return block;
    }

    @Test
    @DisplayName("Valid BlockEntity should have no violations")
    void validBlock_noViolations() {
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(validBlock());
        assertTrue(violations.isEmpty());
    }



    @Test
    @DisplayName("Zero blockFloor should pass @Min(0)")
    void zeroBlockFloor_shouldPass() {
        BlockEntity block = validBlock();
        block.setBlockFloor(0);
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(block);
        assertTrue(violations.isEmpty());
    }




    @Test
    @DisplayName("blockCode = 1 (minimum) should pass @Min(1)")
    void minBlockCode_shouldPass() {
        BlockEntity block = validBlock();
        block.setBlockCode(1);
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(block);
        assertTrue(violations.isEmpty());
    }
}
