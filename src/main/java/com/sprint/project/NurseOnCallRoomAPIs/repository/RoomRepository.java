
package com.sprint.project.NurseOnCallRoomAPIs.repository;

import com.sprint.project.NurseOnCallRoomAPIs.entity.BlockEntity;
import com.sprint.project.NurseOnCallRoomAPIs.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    // Find all rooms in a specific block
    List<RoomEntity> findByBlock(BlockEntity block);

    // Find rooms by availability status
    List<RoomEntity> findByUnavailable(Boolean unavailable);

    // Find rooms by type
    List<RoomEntity> findByType(String type);

    // Find rooms in a block by availability
    @Query("SELECT r FROM RoomEntity r WHERE r.block.blockFloor = :floor " +
            "AND r.block.blockCode = :code AND r.unavailable = :unavailable")
    List<RoomEntity> findByBlockFloorAndBlockCodeAndUnavailable(
            @Param("floor") Integer floor,
            @Param("code") Integer code,
            @Param("unavailable") Boolean unavailable);

    // Find rooms by type within a specific block
    @Query("SELECT r FROM RoomEntity r WHERE r.block.blockFloor = :floor " +
            "AND r.block.blockCode = :code AND r.type = :type")
    List<RoomEntity> findByBlockAndType(@Param("floor") Integer floor,
                                        @Param("code") Integer code,
                                        @Param("type") String type);
    @Query("SELECT r FROM RoomEntity r WHERE r.block.blockFloor = :floor AND r.block.blockCode = :code")
    List<RoomEntity> findRoomsByBlock(Integer floor, Integer code);

}