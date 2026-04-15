package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.project.EntityClasses.OnCall;
import com.sprint.project.EntityClasses.OnCallId;

public interface OnCallRepository extends JpaRepository<OnCall, OnCallId> {
}