package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.response.RoomResponseDTO;
import com.sprint.project.nurseoncallroom.entity.RoomEntity;

import com.sprint.project.nurseoncallroom.exception.*;
import com.sprint.project.nurseoncallroom.repository.RoomRepository;
import com.sprint.project.nurseoncallroom.service.RoomService;

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

    // ✅ GET ALL ROOMS
    @Override
    @Transactional(readOnly = true)
    public List<RoomResponseDTO> getAllRooms() {

        List<RoomEntity> rooms = roomRepository.findAll();

        if (rooms.isEmpty()) {
            throw new BlockCapacityFullException(0, 0); // changed
        }

        return rooms.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET ROOM BY NUMBER
    @Override
    @Transactional(readOnly = true)
    public RoomResponseDTO getRoomByNumber(Integer roomNumber) {

        RoomEntity room = roomRepository.findById(roomNumber)
                .orElseThrow(() -> new NurseNotAvailableException(roomNumber)); // changed

        return toDTO(room);
    }

    // ✅ UPDATE ROOM
    @Override
    public RoomResponseDTO updateRoomAvailability(Integer roomNumber, Boolean unavailable) {

        RoomEntity room = roomRepository.findById(roomNumber)
                .orElseThrow(() -> new NurseNotAvailableException(roomNumber)); // changed

        if (!room.getUnavailable().equals(unavailable)) {
            room.setUnavailable(unavailable);
            room = roomRepository.save(room);
        }

        return toDTO(room);
    }
}