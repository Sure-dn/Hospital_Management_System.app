package com.sprint.project.NurseOnCallRoomAPIs;



import com.sprint.project.NurseOnCallRoomAPIs.dto.response.RoomResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.exception.ResourceNotFoundException;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;
import com.sprint.project.NurseOnCallRoomAPIs.service.implementation.RoomServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

// ══════════════════════════════════════════════════════════════
//  RoomServiceImplTest  –  4 test cases  (2 positive + 2 negative)
// ══════════════════════════════════════════════════════════════
@ExtendWith(MockitoExtension.class)
@DisplayName("RoomServiceImplTest")
class RoomServiceImplTest {

    @Mock  RoomRepository roomRepository;
    @InjectMocks
    RoomServiceImpl roomService;

    private BlockEntity block;
    private RoomEntity  roomEntity;

    @BeforeEach
    void setUp() {
        block      = new BlockEntity(1, 100, "Block A");
        roomEntity = new RoomEntity(10, "ICU", false, block);
    }

    // ── POSITIVE ──────────────────────────────────────────────

    /** Test 5 – Positive
     *  getRoomByNumber should return a correctly mapped RoomResponseDTO
     *  including block details when the room exists.
     */
    @Test
    @DisplayName("[POS-5] getRoomByNumber – existing room – returns DTO with block details")
    void getRoomByNumber_existingRoom_returnsDTOWithBlockDetails() {
        when(roomRepository.findById(10)).thenReturn(Optional.of(roomEntity));

        RoomResponseDTO result = roomService.getRoomByNumber(10);

        assertThat(result.getRoomNumber()).isEqualTo(10);
        assertThat(result.getType()).isEqualTo("ICU");
        assertThat(result.getUnavailable()).isFalse();
        assertThat(result.getBlockFloor()).isEqualTo(1);
        assertThat(result.getBlockCode()).isEqualTo(100);
        assertThat(result.getBlockName()).isEqualTo("Block A");
    }

    /** Test 6 – Positive
     *  updateRoomAvailability should save the room and return the updated DTO
     *  when the unavailable flag is changed to a new value.
     */
    @Test
    @DisplayName("[POS-6] updateRoomAvailability – flag changed – saves and returns updated DTO")
    void updateRoomAvailability_flagChanged_savesAndReturnsUpdatedDTO() {
        when(roomRepository.findById(10)).thenReturn(Optional.of(roomEntity));
        RoomEntity saved = new RoomEntity(10, "ICU", true, block);
        when(roomRepository.save(any(RoomEntity.class))).thenReturn(saved);

        RoomResponseDTO result = roomService.updateRoomAvailability(10, true);

        assertThat(result.getUnavailable()).isTrue();
        verify(roomRepository, times(1)).save(any(RoomEntity.class));
    }

    // ── NEGATIVE ──────────────────────────────────────────────

    /** Test 7 – Negative
     *  getRoomByNumber should throw ResourceNotFoundException
     *  when the room number does not exist.
     */
    @Test
    @DisplayName("[NEG-7] getRoomByNumber – unknown room – throws ResourceNotFoundException")
    void getRoomByNumber_unknownRoom_throwsResourceNotFoundException() {
        when(roomRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roomService.getRoomByNumber(9999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("9999");
    }

    /** Test 8 – Negative
     *  updateRoomAvailability should NOT call save() when the unavailable
     *  flag passed in is the same as the current value.
     */
    @Test
    @DisplayName("[NEG-8] updateRoomAvailability – same flag value – does NOT call save")
    void updateRoomAvailability_sameFlagValue_doesNotCallSave() {
        // roomEntity.unavailable = false; passing false again → no change
        when(roomRepository.findById(10)).thenReturn(Optional.of(roomEntity));

        roomService.updateRoomAvailability(10, false);

        verify(roomRepository, never()).save(any(RoomEntity.class));
    }
}

