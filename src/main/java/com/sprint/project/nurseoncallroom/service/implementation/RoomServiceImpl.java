package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.response.RoomResponseDTO;
import com.sprint.project.nurseoncallroom.entity.RoomEntity;
import com.sprint.project.nurseoncallroom.repository.RoomRepository;
import com.sprint.project.nurseoncallroom.service.RoomService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    // ✅ Constructor Injection (Best Practice)
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // 🔥 COMMON MAPPER METHOD (Single source of truth)
    private RoomResponseDTO mapToRoomDTO(RoomEntity e) {

        RoomResponseDTO dto = new RoomResponseDTO();

        dto.setRoomNumber(e.getRoomNumber());
        dto.setType(e.getType());
        dto.setUnavailable(e.getUnavailable());

        // ✅ Map Block details
        if (e.getBlock() != null) {
            dto.setBlockFloor(e.getBlock().getBlockFloor());
            dto.setBlockCode(e.getBlock().getBlockCode());
            dto.setBlockName(e.getBlock().getName());
        }

        return dto;
    }

    // ✅ GET ALL ROOMS
    @Override
    @Transactional(readOnly = true)
    public List<RoomResponseDTO> getAllRooms() {

        return roomRepository.findAll()
                .stream()
                .map(this::mapToRoomDTO)
                .toList();
    }

    // ✅ GET ROOM BY ID
    @Override
    @Transactional(readOnly = true)
    public RoomResponseDTO getRoomByNumber(Integer roomNumber) {

        RoomEntity room = roomRepository.findById(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return mapToRoomDTO(room);
    }

    // ✅ UPDATE ROOM AVAILABILITY
    @Override
    public RoomResponseDTO updateRoomAvailability(Integer roomNumber, Boolean unavailable) {

        RoomEntity room = roomRepository.findById(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.getUnavailable().equals(unavailable)) {
            room.setUnavailable(unavailable);
            room = roomRepository.save(room);
        }

        return mapToRoomDTO(room);
    }
}