package com.sprint.project.NurseOnCallRoomAPIs.Repository;

import com.sprint.project.NurseOnCallRoomAPIs.Entity.Block;
import com.sprint.project.NurseOnCallRoomAPIs.Entity.BlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, BlockId> {

}

