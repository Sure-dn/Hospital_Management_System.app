package com.sprint.project.ControllerClasses;

import com.sprint.project.EntityClasses.Nurse;
import com.sprint.project.RepositoryClasses.NurseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private NurseRepository nurseRepository;

    // GET all nurses
    @GetMapping("/all")
    public List<Nurse> getAll() {
        return nurseRepository.findAll();
    }

    // ADD nurse
    @PostMapping("/add")
    public Nurse addNurse(@Valid @RequestBody Nurse nurse) {
        return nurseRepository.save(nurse);
    }
}