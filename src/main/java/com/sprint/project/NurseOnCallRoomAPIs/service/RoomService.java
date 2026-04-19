package com.sprint.project.NurseOnCallRoomAPIs.service;

import com.sprint.project.NurseOnCallRoomAPIs.dto.response.RoomResponseDTO;
import java.util.List;

public interface RoomService {
    List<RoomResponseDTO> getAllRooms();
    RoomResponseDTO getRoomByNumber(Integer roomNumber);
    RoomResponseDTO updateRoomAvailability(Integer roomNumber, Boolean unavailable);
}