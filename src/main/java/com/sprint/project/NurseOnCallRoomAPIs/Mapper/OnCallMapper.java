package com.sprint.project.NurseOnCallRoomAPIs.Mapper;


import com.sprint.project.NurseOnCallRoomAPIs.dto.request.OnCallRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.OnCallResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.OnCallEntity;
import org.springframework.stereotype.Component;

@Component
public class OnCallMapper {

    /**
     * Maps OnCallRequestDTO → OnCallEntity.
     * The caller must resolve and supply the NurseEntity separately
     * (typically fetched from NurseRepository using the nurse's employeeId).
     *
     * blockFloor and blockCode are stored as direct fields on OnCallEntity
     * (part of the composite key), so they are mapped directly from the DTO.
     */
    public OnCallEntity toEntity(OnCallRequestDTO dto, NurseEntity nurse) {
        if (dto == null) return null;

        OnCallEntity entity = new OnCallEntity();
        entity.setNurse(nurse);
        entity.setBlockFloor(dto.getBlockFloor());
        entity.setBlockCode(dto.getBlockCode());
        entity.setOnCallStart(dto.getOnCallStart());
        entity.setOnCallEnd(dto.getOnCallEnd());
        return entity;
    }

    /**
     * Maps OnCallEntity → OnCallResponseDTO.
     * Flattens the nested NurseEntity into nurseEmployeeId and nurseName.
     * blockName is not stored on OnCallEntity — the caller may enrich the DTO
     * with it after fetching the BlockEntity from BlockRepository if needed.
     */
    public OnCallResponseDTO toResponseDTO(OnCallEntity entity) {
        if (entity == null) return null;

        OnCallResponseDTO dto = new OnCallResponseDTO();

        if (entity.getNurse() != null) {
            dto.setNurseEmployeeId(entity.getNurse().getEmployeeId());
            dto.setNurseName(entity.getNurse().getName());
        }

        dto.setBlockFloor(entity.getBlockFloor());
        dto.setBlockCode(entity.getBlockCode());
        dto.setOnCallStart(entity.getOnCallStart());
        dto.setOnCallEnd(entity.getOnCallEnd());
        return dto;
    }

    /**
     * Overloaded toResponseDTO that also accepts a blockName string,
     * for when the caller has already resolved the BlockEntity.
     */
    public OnCallResponseDTO toResponseDTO(OnCallEntity entity, String blockName) {
        OnCallResponseDTO dto = toResponseDTO(entity);
        if (dto != null) {
            dto.setBlockName(blockName);
        }
        return dto;
    }

    /**
     * Updates an existing OnCallEntity with values from OnCallRequestDTO.
     * The caller must supply the resolved NurseEntity.
     * Note: Since all fields (nurse, blockFloor, blockCode, onCallStart) are
     * part of the composite key, this is primarily useful for updating onCallEnd.
     */
    public void updateEntityFromDTO(OnCallRequestDTO dto, OnCallEntity entity, NurseEntity nurse) {
        if (dto == null || entity == null) return;

        entity.setNurse(nurse);
        entity.setBlockFloor(dto.getBlockFloor());
        entity.setBlockCode(dto.getBlockCode());
        entity.setOnCallStart(dto.getOnCallStart());
        entity.setOnCallEnd(dto.getOnCallEnd());
    }
}
