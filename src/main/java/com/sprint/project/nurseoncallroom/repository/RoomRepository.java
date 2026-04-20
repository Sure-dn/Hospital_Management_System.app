package com.sprint.project.nurseoncallroom.repository;

import com.sprint.project.nurseoncallroom.entity.BlockEntity;
import com.sprint.project.nurseoncallroom.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    List<RoomEntity> findByBlock(BlockEntity block);

    List<RoomEntity> findByUnavailable(Boolean unavailable);

    List<RoomEntity> findByType(String type);
    List<RoomEntity> findByBlock_BlockFloorAndBlock_BlockCode(Integer floor, Integer code);



    @Query("SELECT r FROM RoomEntity r WHERE r.block.blockFloor = :floor " +
            "AND r.block.blockCode = :code AND r.type = :type")
    List<RoomEntity> findByBlockAndType(@Param("floor") Integer floor,
                                        @Param("code") Integer code,
                                        @Param("type") String type);

    // ✅ IMPORTANT FIX (ADD @Param)
    @Query("SELECT r FROM RoomEntity r WHERE r.block.blockFloor = :floor AND r.block.blockCode = :code")
    List<RoomEntity> findRoomsByBlock(@Param("floor") Integer floor,
                                      @Param("code") Integer code);
}