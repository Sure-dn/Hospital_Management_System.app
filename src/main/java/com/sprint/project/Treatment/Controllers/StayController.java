package com.sprint.project.Treatment.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.Treatment.DTO.ResponseStructure;
import com.sprint.project.Treatment.Entity.StayEntity;
import com.sprint.project.Treatment.Service.StayService;

@RestController
@RequestMapping("/api/stays")
public class StayController {

    @Autowired
    private StayService stayService;

    @PostMapping
    public ResponseEntity<ResponseStructure<StayEntity>> admit(@RequestBody StayEntity stay) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Patient admitted",
                        stayService.admitPatient(stay)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<StayEntity>> get(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Success",
                        stayService.getStayById(id)));
    }

    @PostMapping("/discharge/{id}")
    public ResponseEntity<ResponseStructure<StayEntity>> discharge(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Discharged",
                        stayService.dischargePatient(id)));
    }
}