package me.spike.domain.service;

import lombok.NonNull;
import me.spike.domain.model.*;
import me.spike.domain.model.Error;
import me.spike.persistence.DepartmentRepository;
import me.spike.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final Error INVALID_DEPARTMENT =
            new Error("INVALID_DEPARTMENT", "Invalid department");

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Either<Error, Registration> register(@NonNull Employee employee) {
        final Optional<Department> maybeDepartment = departmentRepository.find(employee.getDepartmentCode());
        return maybeDepartment
                .map(department -> onValidDepartment(employee))
                .orElse(new Left<>(INVALID_DEPARTMENT));
    }

    public List<Employee> search(@NonNull EmployeeSearchCriteria criteria) {
        //TODO Food for thought - Open-Closed principle violation?
        if (criteria.isComplexSearch()) {
            return employeeRepository.find(criteria);
        } else if (criteria.hasDepartmentCode()) {
            return employeeRepository.findByDepartmentCode(criteria.getDepartmentCode());
        } else {
            return employeeRepository.findByName(criteria.getName());
        }
    }

    private Either<Error, Registration> onValidDepartment(@NonNull Employee employee) {
        final String employeeId = employeeRepository.save(employee);
        return new Right<>(new Registration(employeeId));
    }
}
