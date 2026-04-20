package com.sprint.project.nurseoncallroom.controller;

import com.sprint.project.nurseoncallroom.dto.ResponseStructure;
import com.sprint.project.nurseoncallroom.dto.response.RoomResponseDTO;
import com.sprint.project.nurseoncallroom.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // GET /api/rooms
    @GetMapping
    public ResponseEntity<ResponseStructure<List<RoomResponseDTO>>> getAllRooms() {

        List<RoomResponseDTO> rooms = roomService.getAllRooms();

        ResponseStructure<List<RoomResponseDTO>> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Rooms fetched successfully",
                rooms);

        return ResponseEntity.ok(response);
    }

    // GET /api/rooms/{roomNumber}
    @GetMapping("/{roomNumber}")
    public ResponseEntity<ResponseStructure<RoomResponseDTO>> getRoomByNumber(
            @PathVariable Integer roomNumber) {

        RoomResponseDTO responseDTO = roomService.getRoomByNumber(roomNumber);

        ResponseStructure<RoomResponseDTO> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Room fetched successfully",
                responseDTO);

        return ResponseEntity.ok(response);
    }

    // PUT /api/rooms/{roomNumber}/availability
    @PutMapping("/{roomNumber}/availability")
    public ResponseEntity<ResponseStructure<RoomResponseDTO>> updateRoomAvailability(
            @PathVariable Integer roomNumber,
            @RequestParam Boolean unavailable) {

        RoomResponseDTO responseDTO = roomService.updateRoomAvailability(roomNumber, unavailable);

        ResponseStructure<RoomResponseDTO> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Room availability updated successfully",
                responseDTO);

        return ResponseEntity.ok(response);
    }
}
