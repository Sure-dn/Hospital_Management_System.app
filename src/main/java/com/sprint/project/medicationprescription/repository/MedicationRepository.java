package com.sprint.project.medicationprescription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sprint.project.medicationprescription.entity.MedicationEntity;

import java.util.List;

public interface MedicationRepository extends JpaRepository<MedicationEntity, Integer> {

    // Custom Query 1: Search medications by name (partial match, case-insensitive)
    @Query("SELECT m FROM MedicationEntity m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<MedicationEntity> findByNameContainingIgnoreCase(@Param("name") String name);

    // Custom Query 2: Search medications by brand (partial match, case-insensitive)
    @Query("SELECT m FROM MedicationEntity m WHERE LOWER(m.brand) LIKE LOWER(CONCAT('%', :brand, '%'))")
    List<MedicationEntity> findByBrandContainingIgnoreCase(@Param("brand") String brand);

    // Custom Query 3: Search medications by description keyword (partial match, case-insensitive)
    @Query("SELECT m FROM MedicationEntity m WHERE LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<MedicationEntity> findByDescriptionContainingIgnoreCase(@Param("keyword") String keyword);
}