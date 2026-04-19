package com.sprint.project.NurseOnCallRoomAPIs.Mapper;



import com.sprint.project.NurseOnCallRoomAPIs.dto.request.NurseRequestDTO;
import com.sprint.project.NurseOnCallRoomAPIs.dto.response.NurseResponseDTO;
import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import org.springframework.stereotype.Component;

@Component
public class NurseMapper {

    /**
     * Maps NurseRequestDTO → NurseEntity
     */
    public NurseEntity toEntity(NurseRequestDTO dto) {
        if (dto == null) return null;

        NurseEntity entity = new NurseEntity();
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
        entity.setRegistered(dto.getRegistered());
        entity.setSsn(dto.getSsn());
        return entity;
    }

    /**
     * Maps NurseEntity → NurseResponseDTO
     */
    public NurseResponseDTO toResponseDTO(NurseEntity entity) {
        if (entity == null) return null;

        NurseResponseDTO dto = new NurseResponseDTO();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setName(entity.getName());
        dto.setPosition(entity.getPosition());
        dto.setRegistered(entity.getRegistered());
        dto.setSsn(entity.getSsn());
        return dto;
    }

    /**
     * Updates an existing NurseEntity with values from NurseRequestDTO
     * (useful for PUT/PATCH operations)
     */
    public void updateEntityFromDTO(NurseRequestDTO dto, NurseEntity entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
        entity.setRegistered(dto.getRegistered());
        entity.setSsn(dto.getSsn());
    }
}
