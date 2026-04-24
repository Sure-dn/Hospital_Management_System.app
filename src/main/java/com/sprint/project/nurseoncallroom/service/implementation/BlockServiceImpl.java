package com.sprint.project.nurseoncallroom.service.implementation;

import com.sprint.project.nurseoncallroom.dto.response.*;
import com.sprint.project.nurseoncallroom.entity.*;
import com.sprint.project.nurseoncallroom.exception.*;
import com.sprint.project.nurseoncallroom.repository.*;
import com.sprint.project.nurseoncallroom.service.BlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired private BlockRepository blockRepository;
    @Autowired private RoomRepository roomRepository;

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

        BlockEntity block = blockRepository.findById(new BlockId(floor, code))
                .orElseThrow(() -> new BlockNotAvailableException(floor, code));

        List<RoomEntity> rooms =
                roomRepository.findByBlock_BlockFloorAndBlock_BlockCode(floor, code);

        if (rooms.isEmpty()) {
            throw new BlockCapacityFullException(floor, code);
        }

        return rooms.stream().map(r -> {
            RoomResponseDTO dto = new RoomResponseDTO();
            dto.setRoomNumber(r.getRoomNumber());
            dto.setType(r.getType());
            dto.setUnavailable(r.getUnavailable());
            return dto;
        }).collect(Collectors.toList());
    }
}