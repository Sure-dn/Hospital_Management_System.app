package com.sprint.project.physicianDepartmentManagement.service;

import java.util.List;

import com.sprint.project.physicianDepartmentManagement.dto.requestdto.TrainedInRequestDto;
import com.sprint.project.physicianDepartmentManagement.dto.responsedto.TrainedInResponseDto;
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
