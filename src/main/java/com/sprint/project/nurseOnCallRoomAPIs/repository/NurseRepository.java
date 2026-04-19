package com.sprint.project.nurseOnCallRoomAPIs.repository;

import com.sprint.project.nurseOnCallRoomAPIs.entity.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, Integer> {

    // Find by exact position
    List<NurseEntity> findByPosition(String position);

    // Find only registered nurses
    List<NurseEntity> findByRegistered(Boolean registered);

    // Find by SSN (unique)
    Optional<NurseEntity> findBySsn(Integer ssn);

    // Search by name (case-insensitive, partial match)
    @Query("SELECT n FROM NurseEntity n WHERE LOWER(n.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<NurseEntity> searchByName(@Param("keyword") String keyword);

    // Find registered nurses by position
    @Query("SELECT n FROM NurseEntity n WHERE n.position = :position AND n.registered = :registered")
    List<NurseEntity> findByPositionAndRegistered(@Param("position") String position,
                                                  @Param("registered") Boolean registered);
}