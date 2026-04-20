package com.sprint.project.nurseoncallroom.repository;

import com.sprint.project.nurseoncallroom.entity.BlockEntity;
import com.sprint.project.nurseoncallroom.entity.BlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, BlockId> {

    // Find all blocks on a given floor
    List<BlockEntity> findByBlockFloor(Integer blockFloor);

    // Find by name (case-insensitive)
    @Query("SELECT b FROM BlockEntity b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<BlockEntity> searchByName(@Param("name") String name);

    // Check existence by floor+code
    boolean existsByBlockFloorAndBlockCode(Integer blockFloor, Integer blockCode);

    // Find by floor and code directly
    Optional<BlockEntity> findByBlockFloorAndBlockCode(Integer blockFloor, Integer blockCode);
}

