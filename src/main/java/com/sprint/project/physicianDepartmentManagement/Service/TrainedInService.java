package com.sprint.project.physicianDepartmentManagement.Service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.Dto.RequestDto.TrainedInRequestDto;
import com.sprint.project.physicianDepartmentManagement.Dto.ResponseDto.TrainedInResponseDto;
import com.sprint.project.physicianDepartmentManagement.entity.TrainedInId;


public interface TrainedInService {

    TrainedInResponseDto createTraining(TrainedInRequestDto requestDTO);

    List<TrainedInResponseDto> getAllTrainings();

    TrainedInResponseDto getTrainingById(TrainedInId id);

    TrainedInResponseDto updateTraining(TrainedInId id, TrainedInRequestDto requestDTO);

    void deleteTraining(TrainedInId id);
    
    
    List<TrainedInResponseDto> getTrainingsByPhysician(Integer employeeId);

    List<TrainedInResponseDto> getValidTrainingsByPhysician(Integer employeeId);


}
