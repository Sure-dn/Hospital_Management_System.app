package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.response.BlockResponseDTO;
import com.sprint.project.nurseoncallroom.dto.response.RoomResponseDTO;
import com.sprint.project.nurseoncallroom.entity.BlockId;
import com.sprint.project.nurseoncallroom.entity.RoomEntity;
import com.sprint.project.nurseoncallroom.exception.OnCallNotFoundException;
import com.sprint.project.nurseoncallroom.repository.BlockRepository;
import com.sprint.project.nurseoncallroom.repository.RoomRepository;
import com.sprint.project.nurseoncallroom.service.BlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BlockServiceImpl implements BlockService {

    @Autowired private BlockRepository blockRepository;
    @Autowired private RoomRepository roomRepository;

    private RoomResponseDTO toRoomDTO(RoomEntity e) {
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
    public List<BlockResponseDTO> getAllBlocks() {
        return blockRepository.findAll().stream().map(b -> {
            BlockResponseDTO dto = new BlockResponseDTO();
            dto.setBlockFloor(b.getBlockFloor());
            dto.setBlockCode(b.getBlockCode());
            dto.setName(b.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<RoomResponseDTO> getRoomsForBlock(Integer floor, Integer code) {

        blockRepository.findById(new BlockId(floor, code))
                .orElseThrow(() -> new OnCallNotFoundException("Block not found"));

        // 🔥 USE DERIVED QUERY (NOT @Query)
        List<RoomEntity> rooms =
                roomRepository.findByBlock_BlockFloorAndBlock_BlockCode(floor, code);

        return rooms.stream()
                .map(this::toRoomDTO)
                .collect(Collectors.toList());
    }
}