package com.sprint.project.RepositoryClasses;

import com.sprint.project.EntityClasses.Block;
import com.sprint.project.EntityClasses.BlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BlockRepository extends JpaRepository<Block, BlockId> {


}