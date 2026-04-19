package com.sprint.project.NurseOnCallRoomAPIs.Mapper;



import com.sprint.project.NurseOnCallRoomAPIs.dto.request.RoomRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.RoomResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    /**
     * Maps RoomRequestDTO → RoomEntity.
     * The caller must resolve and supply the BlockEntity separately
     * (typically fetched from BlockRepository using blockFloor + blockCode).
     */
    public RoomEntity toEntity(RoomRequestDTO dto, BlockEntity block) {
        if (dto == null) return null;

        RoomEntity entity = new RoomEntity();
        entity.setType(dto.getType());
        entity.setUnavailable(dto.getUnavailable());
        entity.setBlock(block);
        return entity;
    }

    /**
     * Maps RoomEntity → RoomResponseDTO.
     * Flattens the nested BlockEntity into blockFloor, blockCode, and blockName fields.
     */
    public RoomResponseDTO toResponseDTO(RoomEntity entity) {
        if (entity == null) return null;

        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setType(entity.getType());
        dto.setUnavailable(entity.getUnavailable());

        if (entity.getBlock() != null) {
            dto.setBlockFloor(entity.getBlock().getBlockFloor());
            dto.setBlockCode(entity.getBlock().getBlockCode());
            dto.setBlockName(entity.getBlock().getName());
        }

        return dto;
    }

    /**
     * Updates an existing RoomEntity with values from RoomRequestDTO.
     * The caller must resolve and supply the updated BlockEntity separately.
     */
    public void updateEntityFromDTO(RoomRequestDTO dto, RoomEntity entity, BlockEntity block) {
        if (dto == null || entity == null) return;

        entity.setType(dto.getType());
        entity.setUnavailable(dto.getUnavailable());
        entity.setBlock(block);
    }
}
