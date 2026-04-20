package com.sprint.project.nurseoncallroom.service;

import com.sprint.project.nurseoncallroom.dto.response.RoomResponseDTO;
import java.util.List;

public interface RoomService {
    List<RoomResponseDTO> getAllRooms();
    RoomResponseDTO getRoomByNumber(Integer roomNumber);
    RoomResponseDTO updateRoomAvailability(Integer roomNumber, Boolean unavailable);
}