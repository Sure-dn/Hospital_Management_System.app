package com.sprint.project.EntityClasses;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    private Integer roomNumber;

    private String type;
    private Integer capacity;

    public Room() {}

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
