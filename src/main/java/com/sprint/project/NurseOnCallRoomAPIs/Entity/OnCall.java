

package com.sprint.project.NurseOnCallRoomAPIs.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "On_Call")
@IdClass(OnCallId.class)
public class OnCall {

    @Id
    @NotNull(message = "Nurse cannot be null")
    @ManyToOne
    @JoinColumn(name = "Nurse", referencedColumnName = "EmployeeID", nullable = false)
    private Nurse nurse;

    @Id
    @NotNull(message = "Block floor is required")
    @Column(name = "BlockFloor")
    private Integer blockFloor;

    @Id
    @NotNull(message = "Block code is required")
    @Column(name = "BlockCode")
    private Integer blockCode;

    // FK to Block using composite key
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "BlockFloor", referencedColumnName = "BlockFloor", insertable = false, updatable = false),
        @JoinColumn(name = "BlockCode",  referencedColumnName = "BlockCode",  insertable = false, updatable = false)
    })
    private Block block;

    @Id
    @NotNull(message = "OnCall start time is required")
    @Column(name = "OnCallStart", nullable = false)
    private LocalDateTime onCallStart;

    @Id
    @NotNull(message = "OnCall end time is required")
    @Column(name = "OnCallEnd", nullable = false)
    private LocalDateTime onCallEnd;

    public OnCall() {}

    public OnCall(Nurse nurse, Integer blockFloor, Integer blockCode,
                  LocalDateTime onCallStart, LocalDateTime onCallEnd) {
        this.nurse = nurse;
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.onCallStart = onCallStart;
        this.onCallEnd = onCallEnd;
    }

    // Getters and Setters

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Integer getBlockFloor() {
        return blockFloor;
    }

    public void setBlockFloor(Integer blockFloor) {
        this.blockFloor = blockFloor;
    }

    public Integer getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(Integer blockCode) {
        this.blockCode = blockCode;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public LocalDateTime getOnCallStart() {
        return onCallStart;
    }

    public void setOnCallStart(LocalDateTime onCallStart) {
        this.onCallStart = onCallStart;
    }

    public LocalDateTime getOnCallEnd() {
        return onCallEnd;
    }

    public void setOnCallEnd(LocalDateTime onCallEnd) {
        this.onCallEnd = onCallEnd;
    }
}

