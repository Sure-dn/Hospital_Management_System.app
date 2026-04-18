package com.sprint.project.Treatment.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.project.Treatment.DTO.ProceduresRequestDTO;
import com.sprint.project.Treatment.DTO.ProceduresResponseDTO;
import com.sprint.project.Treatment.DTO.ResponseStructure;
import com.sprint.project.Treatment.Service.ProceduresService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/procedures")
public class ProceduresController {

    @Autowired
    private ProceduresService proceduresService;

    @PostMapping
    public ResponseEntity<ResponseStructure<ProceduresResponseDTO>> addProcedure(
            @Valid @RequestBody ProceduresRequestDTO dto) {

        ProceduresRequestDTO entity = new ProceduresRequestDTO();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCost(dto.getCost());

        ProceduresRequestDTO saved = proceduresService.addProcedure(entity);

        ProceduresResponseDTO response =
                new ProceduresResponseDTO(saved.getCode(), saved.getName(), saved.getCost());

        return ResponseEntity.ok(new ResponseStructure<>(true, "Procedure added", response));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseStructure<ProceduresResponseDTO>> getById(@PathVariable Integer code) {

        ProceduresRequestDTO p = proceduresService.getProcedureById(code);

        ProceduresResponseDTO response =
                new ProceduresResponseDTO(p.getCode(), p.getName(), p.getCost());

        return ResponseEntity.ok(new ResponseStructure<>(true, "Success", response));
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<ProceduresRequestDTO>>> getAll() {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "All procedures",
                        proceduresService.getAllProcedures()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<ProceduresResponseDTO>> delete(@PathVariable Integer id) {

        ProceduresRequestDTO deleted = proceduresService.deleteProcedure(id);

        ProceduresResponseDTO response =
                new ProceduresResponseDTO(deleted.getCode(), deleted.getName(), deleted.getCost());

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Deleted successfully", response));
    }
 // 🔍 Search by name
    @GetMapping("/search")
    public ResponseEntity<ResponseStructure<List<ProceduresRequestDTO>>> search(@RequestParam String name) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Search results",
                        proceduresService.searchByName(name)));
    }

    // 💰 Cost range
    @GetMapping("/cost-range")
    public ResponseEntity<ResponseStructure<List<ProceduresRequestDTO>>> costRange(
            @RequestParam Double min,
            @RequestParam Double max) {

        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Cost range results",
                        proceduresService.getByCostRange(min, max)));
    }

    // 💸 Expensive procedures
    @GetMapping("/expensive")
    public ResponseEntity<ResponseStructure<List<ProceduresRequestDTO>>> expensive(@RequestParam Double cost) {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Expensive procedures",
                        proceduresService.getExpensiveProcedures(cost)));
    }

    // 📊 Sort
    @GetMapping("/sorted")
    public ResponseEntity<ResponseStructure<List<ProceduresRequestDTO>>> sort() {
        return ResponseEntity.ok(
                new ResponseStructure<>(true, "Sorted by cost",
                        proceduresService.sortByCost()));
    }
}