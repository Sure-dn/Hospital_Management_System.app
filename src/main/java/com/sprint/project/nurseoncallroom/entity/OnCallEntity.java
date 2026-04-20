package com.sprint.project.nurseoncallroom.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(com.sprint.project.nurseoncallroom.entity.OnCallId.class)
@Table(name = "on_call")
public class OnCallEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private NurseEntity nurse;

    @Id
    @Column(name = "block_floor")
    private Integer blockFloor;

    @Id
    @Column(name = "block_code")
    private Integer blockCode;

    @Id
    @Column(name = "on_call_start")
    private LocalDateTime onCallStart;

    @Column(name = "on_call_end", nullable = false)
    private LocalDateTime onCallEnd;

    public OnCallEntity() {}

    public NurseEntity getNurse() { return nurse; }
    public void setNurse(NurseEntity nurse) { this.nurse = nurse; }

    public Integer getBlockFloor() { return blockFloor; }
    public void setBlockFloor(Integer blockFloor) { this.blockFloor = blockFloor; }

    public Integer getBlockCode() { return blockCode; }
    public void setBlockCode(Integer blockCode) { this.blockCode = blockCode; }

    public LocalDateTime getOnCallStart() { return onCallStart; }
    public void setOnCallStart(LocalDateTime onCallStart) { this.onCallStart = onCallStart; }

    public LocalDateTime getOnCallEnd() { return onCallEnd; }
    public void setOnCallEnd(LocalDateTime onCallEnd) { this.onCallEnd = onCallEnd; }
}