package com.sprint.project.nurseOnCallRoomAPIs.service;

import com.sprint.project.nurseOnCallRoomAPIs.dto.response.RoomResponseDTO;
import java.util.List;

public interface RoomService {
    List<RoomResponseDTO> getAllRooms();
    RoomResponseDTO getRoomByNumber(Integer roomNumber);
    RoomResponseDTO updateRoomAvailability(Integer roomNumber, Boolean unavailable);
}