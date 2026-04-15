package com.sprint.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.sprint.project.Treatment.Entity.ProceduresEntity;
import com.sprint.project.Treatment.Repository.ProceduresRepository;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private ProceduresRepository proceduresRepository;

    @Override
    public void run(String... args) {

        //  CREATE
        ProceduresEntity p = new ProceduresEntity();
        p.setCode(100);
        p.setName("Test Procedure");
        p.setCost(500.0);

        proceduresRepository.save(p);
        System.out.println("Saved!");

        //  READ
        proceduresRepository.findAll().forEach(System.out::println);

        //  UPDATE
        p.setCost(800.0);
        proceduresRepository.save(p);
        System.out.println("Updated!");

        //  DELETE
        proceduresRepository.deleteById(100);
        System.out.println("Deleted!");
    }
}