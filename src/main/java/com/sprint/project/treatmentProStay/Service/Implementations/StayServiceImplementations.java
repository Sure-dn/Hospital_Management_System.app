package com.sprint.project.treatmentprostay.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.treatmentprostay.DTO.StayRequestDTO;
import com.sprint.project.treatmentprostay.entity.StayEntity;
import com.sprint.project.treatmentprostay.repository.StayRepository;
import com.sprint.project.treatmentprostay.service.StayService;
import com.sprint.project.treatmentprostay.exception.InvalidStayException;
import com.sprint.project.treatmentprostay.exception.StayAlreadyExistsException;
import com.sprint.project.treatmentprostay.exception.StayNotFoundException;
import com.sprint.project.exception.BadRequestException;
import com.sprint.project.exception.ResourceNotFoundException;
import com.sprint.project.patientAppointment.Repository.PatientRepository;
import com.sprint.project.NurseOnCallRoomAPIs.repository.RoomRepository;

@Service
public class StayServiceImplementations implements StayService {

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public StayEntity admitPatient(StayRequestDTO dto) {

    	if (dto.getStayStart().isAfter(LocalDateTime.now())) {
    	    throw new InvalidStayException("Stay start cannot be in future");
    	}

    	if (stayRepository.existsById(dto.getStayId())) {
    	    throw new StayAlreadyExistsException("Stay already exists");
    	}

        StayEntity stay = new StayEntity();

        stay.setStayId(dto.getStayId());

        stay.setPatient(
                patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new StayNotFoundException("Stay not found")) 
        );

        stay.setRoom(
                roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new StayNotFoundException("Stay not found with id: "))
        );

        stay.setStayStart(dto.getStayStart());
        stay.setStayEnd(dto.getStayEnd());

        return stayRepository.save(stay);
    }
    @Override
    public StayEntity getStayById(Integer stayId) {
        return stayRepository.findById(stayId)
                .orElseThrow(() -> new ResourceNotFoundException("Stay not found"));
    }

    @Override
    public List<StayEntity> getAllStays() {
        return stayRepository.findAll();
    }

    @Override
    public StayEntity dischargePatient(Integer stayId) {
        StayEntity stay = getStayById(stayId);
        stay.setStayEnd(LocalDateTime.now());
        return stayRepository.save(stay);
    }
    @Override
    public String deleteStay(int id) {
        StayEntity stay = stayRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Stay not found with id: " + id));

        stayRepository.delete(stay);

        return "Stay deleted successfully";
    }
}