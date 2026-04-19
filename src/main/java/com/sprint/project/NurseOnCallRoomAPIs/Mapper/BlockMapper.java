package com.sprint.project.NurseOnCallRoomAPIs.Mapper;


import com.sprint.project.NurseOnCallRoomAPIs.dto.request.BlockRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.BlockResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import org.springframework.stereotype.Component;

@Component
public class BlockMapper {

    /**
     * Maps BlockRequestDTO → BlockEntity
     */
    public BlockEntity toEntity(BlockRequestDTO dto) {
        if (dto == null) return null;

        BlockEntity entity = new BlockEntity();
        entity.setBlockFloor(dto.getBlockFloor());
        entity.setBlockCode(dto.getBlockCode());
        entity.setName(dto.getName());
        return entity;
    }

    /**
     * Maps BlockEntity → BlockResponseDTO
     */
    public BlockResponseDTO toResponseDTO(BlockEntity entity) {
        if (entity == null) return null;

        BlockResponseDTO dto = new BlockResponseDTO();
        dto.setBlockFloor(entity.getBlockFloor());
        dto.setBlockCode(entity.getBlockCode());
        dto.setName(entity.getName());
        return dto;
    }

    /**
     * Updates an existing BlockEntity with values from BlockRequestDTO
     * (useful for PUT/PATCH operations)
     */
    public void updateEntityFromDTO(BlockRequestDTO dto, BlockEntity entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        // Note: composite key fields (blockFloor, blockCode) are intentionally
        // not updated here — they form the primary key and should not change.
    }
}
