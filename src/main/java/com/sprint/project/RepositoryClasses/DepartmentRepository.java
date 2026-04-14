package com.sprint.project.RepositoryClasses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.EntityClasses.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
