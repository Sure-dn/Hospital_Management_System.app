package com.sprint.project.NurseOnCallRoomAPIs;



import com.sprint.project.NurseOnCallRoomAPIs.dto.response.BlockResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.RoomResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockId;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.exception.ResourceNotFoundException;
import com.sprint.project.NurseOnCallRoomAPIs.repository.BlockRepository;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;
import com.sprint.project.NurseOnCallRoomAPIs.service.implementation.BlockServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

// ══════════════════════════════════════════════════════════════
//  BlockServiceImplTest  –  4 test cases  (2 positive + 2 negative)
// ══════════════════════════════════════════════════════════════
@ExtendWith(MockitoExtension.class)
@DisplayName("BlockServiceImplTest")
class BlockServiceImplTest {

    @Mock  BlockRepository blockRepository;
    @Mock  RoomRepository  roomRepository;
    @InjectMocks
    BlockServiceImpl blockService;

    private BlockEntity blockEntity;
    private RoomEntity  roomEntity;

    @BeforeEach
    void setUp() {
        blockEntity = new BlockEntity(1, 100, "Alpha Wing");
        roomEntity  = new RoomEntity(10, "ICU", false, blockEntity);
    }

    // ── POSITIVE ──────────────────────────────────────────────

    /** Test 9 – Positive
     *  getAllBlocks should return a correctly mapped list of BlockResponseDTOs
     *  with all fields populated.
     */
    @Test
    @DisplayName("[POS-9] getAllBlocks – blocks exist – returns correctly mapped DTOs")
    void getAllBlocks_blocksExist_returnsMappedDTOs() {
        when(blockRepository.findAll()).thenReturn(List.of(blockEntity));

        List<BlockResponseDTO> result = blockService.getAllBlocks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Alpha Wing");
        assertThat(result.get(0).getBlockFloor()).isEqualTo(1);
        assertThat(result.get(0).getBlockCode()).isEqualTo(100);
    }

    /** Test 10 – Positive
     *  getRoomsForBlock should return a list of RoomResponseDTOs
     *  when the block exists and has rooms assigned.
     */
    @Test
    @DisplayName("[POS-10] getRoomsForBlock – block has rooms – returns room DTOs")
    void getRoomsForBlock_blockHasRooms_returnsRoomDTOs() {
        when(blockRepository.findById(new BlockId(1, 100)))
                .thenReturn(Optional.of(blockEntity));
        when(roomRepository.findRoomsByBlock(1, 100))
                .thenReturn(List.of(roomEntity));

        List<RoomResponseDTO> result = blockService.getRoomsForBlock(1, 100);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getType()).isEqualTo("ICU");
        assertThat(result.get(0).getRoomNumber()).isEqualTo(10);
    }

    // ── NEGATIVE ──────────────────────────────────────────────

    /** Test 11 – Negative
     *  getRoomsForBlock should throw ResourceNotFoundException
     *  when no block exists for the given floor and code.
     */
    @Test
    @DisplayName("[NEG-11] getRoomsForBlock – block not found – throws ResourceNotFoundException")
    void getRoomsForBlock_blockNotFound_throwsResourceNotFoundException() {
        when(blockRepository.findById(new BlockId(9, 999)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> blockService.getRoomsForBlock(9, 999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Block not found")
                .hasMessageContaining("9")
                .hasMessageContaining("999");

        verifyNoInteractions(roomRepository);
    }

    /** Test 12 – Negative
     *  getRoomsForBlock should return an empty list (not throw)
     *  when the block exists but has no rooms assigned to it.
     */
    @Test
    @DisplayName("[NEG-12] getRoomsForBlock – block exists but no rooms – returns empty list")
    void getRoomsForBlock_blockExistsNoRooms_returnsEmptyList() {
        when(blockRepository.findById(new BlockId(1, 100)))
                .thenReturn(Optional.of(blockEntity));
        when(roomRepository.findRoomsByBlock(1, 100))
                .thenReturn(List.of());

        List<RoomResponseDTO> result = blockService.getRoomsForBlock(1, 100);

        assertThat(result).isEmpty();
    }
}

