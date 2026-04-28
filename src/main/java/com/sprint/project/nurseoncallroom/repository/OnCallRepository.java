package com.sprint.project.nurseoncallroom.repository;

import com.sprint.project.nurseoncallroom.entity.NurseEntity;
import com.sprint.project.nurseoncallroom.entity.OnCallEntity;
import com.sprint.project.nurseoncallroom.entity.OnCallId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OnCallRepository extends JpaRepository<OnCallEntity, OnCallId> {

    // ✅ Get all shifts for a nurse
    List<OnCallEntity> findByNurse(NurseEntity nurse);

    // ✅ Get shifts by block
    List<OnCallEntity> findByBlockFloorAndBlockCode(Integer floor, Integer code);

    // ✅ Duplicate check (exact same time)
    boolean existsByNurseAndOnCallStartAndOnCallEnd(
            NurseEntity nurse,
            LocalDateTime start,
            LocalDateTime end
    );

    // 🔥 CORRECT OVERLAP CHECK (MOST IMPORTANT)
    @Query("""
        SELECT COUNT(o) > 0 FROM OnCallEntity o
        WHERE o.nurse = :nurse
        AND (:start < o.onCallEnd AND :end > o.onCallStart)
    """)
    boolean existsOverlap(
            @Param("nurse") NurseEntity nurse,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    // Delete all shifts of nurse
    @Modifying
    void deleteByNurse(NurseEntity nurse);
}