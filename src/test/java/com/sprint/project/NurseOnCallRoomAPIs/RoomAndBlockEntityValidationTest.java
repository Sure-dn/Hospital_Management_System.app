package com.sprint.project.NurseOnCallRoomAPIs;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.Entity.RoomEntity;
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
    @DisplayName("Null roomNumber should fail @NotNull")
    void nullRoomNumber_shouldViolate() {
        RoomEntity room = validRoom();
        room.setRoomNumber(null);
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("roomNumber")));
    }

    @Test
    @DisplayName("Negative roomNumber should fail @Positive")
    void negativeRoomNumber_shouldViolate() {
        RoomEntity room = validRoom();
        room.setRoomNumber(-5);
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("roomNumber")));
    }

    @Test
    @DisplayName("Zero roomNumber should fail @Positive")
    void zeroRoomNumber_shouldViolate() {
        RoomEntity room = validRoom();
        room.setRoomNumber(0);
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Blank roomType should fail @NotBlank")
    void blankRoomType_shouldViolate() {
        RoomEntity room = validRoom();
        room.setRoomType("  ");
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("roomType")));
    }

    @Test
    @DisplayName("RoomType shorter than 3 chars should fail @Size")
    void shortRoomType_shouldViolate() {
        RoomEntity room = validRoom();
        room.setRoomType("AB");
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("RoomType longer than 20 chars should fail @Size")
    void longRoomType_shouldViolate() {
        RoomEntity room = validRoom();
        room.setRoomType("A".repeat(21));
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
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

    @Test
    @DisplayName("Null block should fail @NotNull")
    void nullBlock_shouldViolate() {
        RoomEntity room = validRoom();
        room.setBlock(null);
        Set<ConstraintViolation<RoomEntity>> violations = validator.validate(room);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("block")));
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
    @DisplayName("Null blockFloor should fail @NotNull")
    void nullBlockFloor_shouldViolate() {
        BlockEntity block = validBlock();
        block.setBlockFloor(null);
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(block);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("blockFloor")));
    }

    @Test
    @DisplayName("Negative blockFloor should fail @Min(0)")
    void negativeBlockFloor_shouldViolate() {
        BlockEntity block = validBlock();
        block.setBlockFloor(-1);
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(block);
        assertFalse(violations.isEmpty());
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
    @DisplayName("Null blockCode should fail @NotNull")
    void nullBlockCode_shouldViolate() {
        BlockEntity block = validBlock();
        block.setBlockCode(null);
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(block);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("blockCode")));
    }

    @Test
    @DisplayName("Zero blockCode should fail @Min(1)")
    void zeroBlockCode_shouldViolate() {
        BlockEntity block = validBlock();
        block.setBlockCode(0);
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(block);
        assertFalse(violations.isEmpty());
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
