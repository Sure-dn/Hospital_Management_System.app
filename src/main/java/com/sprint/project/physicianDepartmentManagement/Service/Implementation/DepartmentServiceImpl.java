package com.sprint.project.physicianDepartmentManagement.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.project.physicianDepartmentManagement.Entity.DepartmentEntity;
import com.sprint.project.physicianDepartmentManagement.Entity.PhysicianEntity;
import com.sprint.project.physicianDepartmentManagement.Exception.DuplicateResourceException;
import com.sprint.project.physicianDepartmentManagement.Exception.ResourceNotFoundException;
import com.sprint.project.physicianDepartmentManagement.Repository.DepartmentRepository;
import com.sprint.project.physicianDepartmentManagement.Repository.PhysicianRepository;
import com.sprint.project.physicianDepartmentManagement.Service.DepartmentService;
import com.sprint.project.physicianDepartmentManagement.Service.Implementation.DepartmentServiceImpl;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
    private final PhysicianRepository physicianRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                  PhysicianRepository physicianRepository) {
        this.departmentRepository = departmentRepository;
        this.physicianRepository = physicianRepository;
    }
    @Override
    public DepartmentEntity createDepartment(DepartmentEntity department) {
        // Business Rule: Department ID must be unique
        if (departmentRepository.existsById(department.getDepartmentId())) {
            throw new DuplicateResourceException("Department", "DepartmentID", department.getDepartmentId());
        }
        // Business Rule: Head physician must exist
        Integer headId = department.getHead().getEmployeeId();
        PhysicianEntity head = physicianRepository.findById(headId)
            .orElseThrow(() -> new ResourceNotFoundException("Physician (Head)", "EmployeeID", headId));
        department.setHead(head);
        return departmentRepository.save(department);
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentID", departmentId));
    }

    @Override
    public DepartmentEntity updateDepartment(Integer departmentId, DepartmentEntity updatedDepartment) {
        DepartmentEntity existing = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentID", departmentId));

        existing.setName(updatedDepartment.getName());

        // Business Rule: Validate the new head physician exists
        Integer headId = updatedDepartment.getHead().getEmployeeId();
        PhysicianEntity head = physicianRepository.findById(headId)
            .orElseThrow(() -> new ResourceNotFoundException("Physician (Head)", "EmployeeID", headId));
        existing.setHead(head);

        return departmentRepository.save(existing);
    }

    @Override
    public PhysicianEntity getDepartmentHead(Integer departmentId) {
        DepartmentEntity department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentID", departmentId));
        return department.getHead();
    }
}
