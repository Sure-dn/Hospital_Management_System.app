package com.sprint.project.NurseOnCallRoomAPIs.service.implementation;

import com.sprint.project.NurseOnCallRoomAPIs.dto.response.RoomResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import com.sprint.project.NurseOnCallRoomAPIs.exception.ResourceNotFoundException;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;
import com.sprint.project.NurseOnCallRoomAPIs.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private RoomResponseDTO toDTO(RoomEntity e) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setRoomNumber(e.getRoomNumber());
        dto.setType(e.getType());
        dto.setUnavailable(e.getUnavailable());
        if (e.getBlock() != null) {
            dto.setBlockFloor(e.getBlock().getBlockFloor());
            dto.setBlockCode(e.getBlock().getBlockCode());
            dto.setBlockName(e.getBlock().getName());
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponseDTO getRoomByNumber(Integer roomNumber) {
        return toDTO(roomRepository.findById(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "roomNumber", roomNumber)));
    }

    @Override
    public RoomResponseDTO updateRoomAvailability(Integer roomNumber, Boolean unavailable) {
        RoomEntity room = roomRepository.findById(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "roomNumber", roomNumber));
        if (!room.getUnavailable().equals(unavailable)) {
            room.setUnavailable(unavailable);
            room = roomRepository.save(room);
        }
        return toDTO(room);
    }
}