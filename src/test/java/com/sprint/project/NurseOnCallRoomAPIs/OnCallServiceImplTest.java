package com.sprint.project.NurseOnCallRoomAPIs;



import com.sprint.project.NurseOnCallRoomAPIs.dto.request.OnCallRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.OnCallResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.OnCallEntity;
import com.sprint.project.NurseOnCallRoomAPIs.exception.ResourceNotFoundException;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;
import com.sprint.project.NurseOnCallRoomAPIs.repository.OnCallRepository;
import com.sprint.project.NurseOnCallRoomAPIs.service.implementation.OnCallServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

// ══════════════════════════════════════════════════════════════
//  OnCallServiceImplTest  –  3 test cases  (2 positive + 1 negative)
// ══════════════════════════════════════════════════════════════
@ExtendWith(MockitoExtension.class)
@DisplayName("OnCallServiceImplTest")
class OnCallServiceImplTest {

    @Mock  OnCallRepository onCallRepository;
    @Mock  NurseRepository  nurseRepository;
    @InjectMocks
    OnCallServiceImpl onCallService;

    private NurseEntity      nurseEntity;
    private OnCallRequestDTO requestDTO;
    private OnCallEntity     onCallEntity;

    private static final LocalDateTime START =
            LocalDateTime.of(2026, 4, 18, 8, 0);
    private static final LocalDateTime END   =
            LocalDateTime.of(2026, 4, 18, 16, 0);

    @BeforeEach
    void setUp() {
        nurseEntity = new NurseEntity(101, "Alice Smith", "Head Nurse", true, 111001);

        requestDTO = new OnCallRequestDTO();
        requestDTO.setBlockFloor(1);
        requestDTO.setBlockCode(100);
        requestDTO.setOnCallStart(START);
        requestDTO.setOnCallEnd(END);

        onCallEntity = new OnCallEntity();
        onCallEntity.setNurse(nurseEntity);
        onCallEntity.setBlockFloor(1);
        onCallEntity.setBlockCode(100);
        onCallEntity.setOnCallStart(START);
        onCallEntity.setOnCallEnd(END);
    }

    // ── POSITIVE ──────────────────────────────────────────────

    /** Test 13 – Positive
     *  assignOnCall should persist the on-call shift and return a correctly
     *  mapped OnCallResponseDTO when the nurse exists.
     */
    @Test
    @DisplayName("[POS-13] assignOnCall – nurse exists – saves shift and returns mapped DTO")
    void assignOnCall_nurseExists_savesAndReturnsMappedDTO() {
        when(nurseRepository.findById(101)).thenReturn(Optional.of(nurseEntity));
        when(onCallRepository.save(any(OnCallEntity.class))).thenReturn(onCallEntity);

        OnCallResponseDTO result = onCallService.assignOnCall(101, requestDTO);

        assertThat(result.getNurseEmployeeId()).isEqualTo(101);
        assertThat(result.getNurseName()).isEqualTo("Alice Smith");
        assertThat(result.getBlockFloor()).isEqualTo(1);
        assertThat(result.getBlockCode()).isEqualTo(100);
        assertThat(result.getOnCallStart()).isEqualTo(START);
        assertThat(result.getOnCallEnd()).isEqualTo(END);
        verify(onCallRepository, times(1)).save(any(OnCallEntity.class));
    }

    /** Test 14 – Positive
     *  getOnCallByNurse should return all on-call shifts for an existing nurse
     *  as a list of OnCallResponseDTOs.
     */
    @Test
    @DisplayName("[POS-14] getOnCallByNurse – nurse exists – returns list of shift DTOs")
    void getOnCallByNurse_nurseExists_returnsShiftDTOs() {
        when(nurseRepository.findById(101)).thenReturn(Optional.of(nurseEntity));
        when(onCallRepository.findByNurse(nurseEntity)).thenReturn(List.of(onCallEntity));

        List<OnCallResponseDTO> result = onCallService.getOnCallByNurse(101);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNurseName()).isEqualTo("Alice Smith");
        assertThat(result.get(0).getOnCallStart()).isEqualTo(START);
    }

    // ── NEGATIVE ──────────────────────────────────────────────

    /** Test 15 – Negative
     *  assignOnCall should throw ResourceNotFoundException
     *  when the employeeId does not exist, and must NOT call save().
     */
    @Test
    @DisplayName("[NEG-15] assignOnCall – nurse not found – throws ResourceNotFoundException, no save")
    void assignOnCall_nurseNotFound_throwsResourceNotFoundExceptionAndNoSave() {
        when(nurseRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> onCallService.assignOnCall(9999, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("9999");

        verify(onCallRepository, never()).save(any(OnCallEntity.class));
    }
}
