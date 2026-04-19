package com.sprint.project.NurseOnCallRoomAPIs.service;

import com.sprint.project.NurseOnCallRoomAPIs.dto.response.BlockResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.RoomResponseDTO;

import java.util.List;

public interface BlockService {

    /** Return all blocks. */
    List<BlockResponseDTO> getAllBlocks();

    /**
     * Return all rooms in a specific block.
     * Throws ResourceNotFoundException if the block does not exist.
     */
    List<RoomResponseDTO> getRoomsForBlock(Integer floor, Integer code);
}