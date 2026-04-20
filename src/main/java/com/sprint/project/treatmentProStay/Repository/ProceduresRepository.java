package com.sprint.project.treatmentprostay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.treatmentprostay.entity.ProceduresEntity;

public interface ProceduresRepository extends JpaRepository<ProceduresEntity, Integer> {
	// 1. Find by exact name
    List<ProceduresEntity> findByName(String name);

    // 2. Search by name (LIKE)
    List<ProceduresEntity> findByNameContainingIgnoreCase(String name);

    // 3. Cost range
    List<ProceduresEntity> findByCostBetween(Double min, Double max);

    // 4. Greater than cost
    List<ProceduresEntity> findByCostGreaterThan(Double cost);

    // 5. Sort by cost ascending
    List<ProceduresEntity> findAllByOrderByCostAsc();

    // 6. Check duplicate by name
    boolean existsByName(String name);

    // 7. Custom JPQL query (advanced)
    @Query("SELECT p FROM ProceduresEntity p WHERE p.cost > ?1")
    List<ProceduresEntity> getExpensiveProcedures(Double cost);
}