package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.response.*;
import com.sprint.project.nurseoncallroom.entity.*;
import com.sprint.project.nurseoncallroom.exception.*;
import com.sprint.project.nurseoncallroom.repository.*;
import com.sprint.project.nurseoncallroom.service.BlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private RoomRepository roomRepository;

    // 🔥 COMMON MAPPER METHOD
    private RoomResponseDTO mapToRoomDTO(RoomEntity r) {

        RoomResponseDTO dto = new RoomResponseDTO();

        dto.setRoomNumber(r.getRoomNumber());
        dto.setType(r.getType());
        dto.setUnavailable(r.getUnavailable());

        if (r.getBlock() != null) {
            dto.setBlockFloor(r.getBlock().getBlockFloor());
            dto.setBlockCode(r.getBlock().getBlockCode());
            dto.setBlockName(r.getBlock().getName());
        }

        return dto;
    }

    @Override
    public List<BlockResponseDTO> getAllBlocks() {

        return blockRepository.findAll().stream()
                .map(b -> new BlockResponseDTO(
                        b.getBlockFloor(),
                        b.getBlockCode(),
                        b.getName()
                ))
                .toList();
    }

    @Override
    public List<RoomResponseDTO> getRoomsForBlock(Integer floor, Integer code) {

        BlockEntity block = blockRepository.findById(new BlockId(floor, code))
                .orElseThrow(() -> new BlockNotAvailableException(floor, code));

        List<RoomEntity> rooms =
                roomRepository.findByBlock_BlockFloorAndBlock_BlockCode(floor, code);



        // 🔥 USE MAPPER METHOD
        return rooms.stream()
                .map(this::mapToRoomDTO)
                .toList();
    }
}