package com.sprint.project.NurseOnCallRoomAPIs.repository;

import com.sprint.project.NurseOnCallRoomAPIs.entity.NurseEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.OnCallEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.OnCallId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OnCallRepository extends JpaRepository<OnCallEntity, OnCallId> {

    // All shifts for a given nurse
    List<OnCallEntity> findByNurse(NurseEntity nurse);

    // All shifts on a given block
    List<OnCallEntity> findByBlockFloorAndBlockCode(Integer floor, Integer code);

    // All shifts within time window
    @Query("""
        SELECT o FROM OnCallEntity o
        WHERE o.onCallStart >= :from AND o.onCallEnd <= :to
    """)
    List<OnCallEntity> findShiftsWithinWindow(@Param("from") LocalDateTime from,
                                              @Param("to")   LocalDateTime to);

    // Currently active shifts
    @Query("""
        SELECT o FROM OnCallEntity o
        WHERE o.onCallStart <= :now AND o.onCallEnd >= :now
    """)
    List<OnCallEntity> findCurrentlyOnCall(@Param("now") LocalDateTime now);

    // Overlapping shift check
    @Query("""
        SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END
        FROM OnCallEntity o
        WHERE o.nurse = :nurse
          AND o.blockFloor = :floor
          AND o.blockCode  = :code
          AND (:start < o.onCallEnd AND :end > o.onCallStart)
    """)
    boolean existsOverlappingShift(@Param("nurse") NurseEntity nurse,
                                   @Param("floor") Integer floor,
                                   @Param("code")  Integer code,
                                   @Param("start") LocalDateTime start,
                                   @Param("end")   LocalDateTime end);

    // Delete all shifts of nurse
    @Modifying
    void deleteByNurse(NurseEntity nurse);
}