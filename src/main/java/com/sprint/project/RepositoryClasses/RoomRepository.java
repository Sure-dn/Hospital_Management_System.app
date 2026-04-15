package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.EntityClasses.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {

}
