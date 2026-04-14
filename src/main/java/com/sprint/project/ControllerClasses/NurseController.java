package com.sprint.project.ControllerClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.EntityClasses.Nurse;
import com.sprint.project.RepositoryClasses.NurseRepository;

import java.util.List;

@RestController
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseRepository nurseRepository;

    // CREATE
    @PostMapping
    public Nurse addNurse(@RequestBody Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    // READ ALL
    @GetMapping
    public List<Nurse> getAllNurses() {
        return NurseRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Nurse getNurse(@PathVariable Integer id) {
        return nurseRepository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Nurse updateNurse(@PathVariable Integer id, @RequestBody Nurse nurse) {
        nurse.setEmployeeID(id);
        return nurseRepository.save(nurse);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteNurse(@PathVariable Integer id) {
        nurseRepository.deleteById(id);
        return "Deleted Successfully";
    }
}