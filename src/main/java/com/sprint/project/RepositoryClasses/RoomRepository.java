package com.sprint.project.RepositoryClasses;

import com.sprint.project.EntityClasses.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
