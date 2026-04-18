package com.sprint.project.Treatment.Service.Implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.Treatment.DTO.StayRequestDTO;
import com.sprint.project.Treatment.Entity.StayEntity;
import com.sprint.project.Treatment.Repository.StayRepository;
import com.sprint.project.Treatment.Service.StayService;
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
            throw new BadRequestException("Stay start cannot be in future");
        }

        StayEntity stay = new StayEntity();

        stay.setStayId(dto.getStayId());

        stay.setPatient(
                patientRepository.findById(dto.getPatientId().longValue())
                        .orElseThrow(() -> new ResourceNotFoundException("Patient not found"))
        );

        stay.setRoom(
                roomRepository.findById(dto.getRoomId())
                        .orElseThrow(() -> new ResourceNotFoundException("Room not found"))
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
}