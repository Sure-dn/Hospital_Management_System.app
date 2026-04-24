package com.sprint.project.nurseoncallroom.controller;

import com.sprint.project.nurseoncallroom.dto.ResponseStructure;
import com.sprint.project.nurseoncallroom.dto.response.BlockResponseDTO;
import com.sprint.project.nurseoncallroom.dto.response.RoomResponseDTO;
import com.sprint.project.nurseoncallroom.service.BlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/blocks")
public class BlockController {

    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    // GET /api/blocks
    @GetMapping
    public ResponseEntity<ResponseStructure<List<BlockResponseDTO>>> getAllBlocks() {

        List<BlockResponseDTO> blocks = blockService.getAllBlocks();

        ResponseStructure<List<BlockResponseDTO>> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Blocks fetched successfully",
                blocks);

        return ResponseEntity.ok(response);
    }

    // GET /api/blocks/{floor}/{code}/rooms
    @GetMapping("/{floor}/{code}/rooms")
    public ResponseEntity<ResponseStructure<List<RoomResponseDTO>>> getRoomsForBlock(
            @PathVariable Integer floor,
            @PathVariable Integer code) {

        List<RoomResponseDTO> rooms = blockService.getRoomsForBlock(floor, code);

        ResponseStructure<List<RoomResponseDTO>> response = ResponseStructure.success(
                HttpStatus.OK.value(),
                "Rooms fetched for block [floor=" + floor + ", code=" + code + "]",
                rooms);

        return ResponseEntity.ok(response);
    }
}
