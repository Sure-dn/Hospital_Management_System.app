package com.sprint.project.NurseOnCallRoomAPIs;

import com.sprint.project.NurseOnCallRoomAPIs.dto.request.NurseRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.NurseResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.exception.DuplicateResourceException;
import com.sprint.project.NurseOnCallRoomAPIs.exception.ResourceNotFoundException;
import com.sprint.project.NurseOnCallRoomAPIs.repository.NurseRepository;
import com.sprint.project.NurseOnCallRoomAPIs.service.implementation.NurseServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

// ══════════════════════════════════════════════════════════════
//  NurseServiceImplTest  –  4 test cases  (2 positive + 2 negative)
// ══════════════════════════════════════════════════════════════
@ExtendWith(MockitoExtension.class)
@DisplayName("NurseServiceImplTest")
class NurseServiceImplTest {

    @Mock  NurseRepository nurseRepository;
    @InjectMocks
    NurseServiceImpl nurseService;

    private NurseRequestDTO requestDTO;
    private NurseEntity     nurseEntity;

    @BeforeEach
    void setUp() {
        requestDTO = new NurseRequestDTO();
        requestDTO.setEmployeeId(101);
        requestDTO.setName("Alice Smith");
        requestDTO.setPosition("Head Nurse");
        requestDTO.setRegistered(true);
        requestDTO.setSsn(111001);

        nurseEntity = new NurseEntity(101, "Alice Smith", "Head Nurse", true, 111001);
    }

    // ── POSITIVE ──────────────────────────────────────────────

    /** Test 1 – Positive
     *  createNurse should save and return a correctly mapped DTO
     *  when the employeeId does not already exist.
     */
    @Test
    @DisplayName("[POS-1] createNurse – new ID – saves and returns mapped DTO")
    void createNurse_newId_savesAndReturnsMappedDTO() {
        when(nurseRepository.existsById(101)).thenReturn(false);
        when(nurseRepository.save(any(NurseEntity.class))).thenReturn(nurseEntity);

        NurseResponseDTO result = nurseService.createNurse(requestDTO);

        assertThat(result.getEmployeeId()).isEqualTo(101);
        assertThat(result.getName()).isEqualTo("Alice Smith");
        assertThat(result.getPosition()).isEqualTo("Head Nurse");
        assertThat(result.getRegistered()).isTrue();
        verify(nurseRepository, times(1)).save(any(NurseEntity.class));
    }

    /** Test 2 – Positive
     *  updateNurse should update the name and position of an existing nurse
     *  and return the updated DTO.
     */
    @Test
    @DisplayName("[POS-2] updateNurse – existing ID – updates name and position")
    void updateNurse_existingId_updatesNameAndPosition() {
        NurseRequestDTO updateReq = new NurseRequestDTO();
        updateReq.setName("Alice Updated");
        updateReq.setPosition("Senior Nurse");
        updateReq.setRegistered(true);
        updateReq.setSsn(111001);

        NurseEntity updatedEntity =
                new NurseEntity(101, "Alice Updated", "Senior Nurse", true, 111001);

        when(nurseRepository.findById(101)).thenReturn(Optional.of(nurseEntity));
        when(nurseRepository.save(any(NurseEntity.class))).thenReturn(updatedEntity);

        NurseResponseDTO result = nurseService.updateNurse(101, updateReq);

        assertThat(result.getName()).isEqualTo("Alice Updated");
        assertThat(result.getPosition()).isEqualTo("Senior Nurse");
        verify(nurseRepository).save(any(NurseEntity.class));
    }

    // ── NEGATIVE ──────────────────────────────────────────────

    /** Test 3 – Negative
     *  createNurse should throw DuplicateResourceException
     *  when the employeeId already exists, and must NOT call save().
     */
    @Test
    @DisplayName("[NEG-3] createNurse – duplicate ID – throws DuplicateResourceException, no save")
    void createNurse_duplicateId_throwsDuplicateResourceExceptionAndNoSave() {
        when(nurseRepository.existsById(101)).thenReturn(true);

        assertThatThrownBy(() -> nurseService.createNurse(requestDTO))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("101");

        verify(nurseRepository, never()).save(any());
    }

    /** Test 4 – Negative
     *  getNurseById should throw ResourceNotFoundException
     *  when the employeeId does not exist in the repository.
     */
    @Test
    @DisplayName("[NEG-4] getNurseById – unknown ID – throws ResourceNotFoundException")
    void getNurseById_unknownId_throwsResourceNotFoundException() {
        when(nurseRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> nurseService.getNurseById(9999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("9999");
    }
}
