package me.spike.domain.service;

import me.spike.domain.model.Error;
import me.spike.domain.model.*;
import me.spike.persistence.DepartmentRepository;
import me.spike.persistence.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

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

    public Either<Registration> register(@NotNull Employee employee) {
        final Optional<Department> maybeDepartment = departmentRepository.find(employee.getDepartmentCode());
        return maybeDepartment
                .map(department -> onValidDepartment(employee))
                .orElse(new Either<>(INVALID_DEPARTMENT));
    }

    private Either<Registration> onValidDepartment(Employee employee) {
        final String newEmployeeId = employeeRepository.save(employee);
        return new Either<>(new Registration(newEmployeeId));
    }

    public List<Employee> search(@NotNull EmployeeSearchCriteria criteria) {
        if (criteria.isComplexSearch()) {
            return employeeRepository.find(criteria);
        } else if (criteria.hasDepartmentCode()) {
            return employeeRepository.findByDepartmentCode(criteria.getDepartmentCode());
        } else {
            return employeeRepository.findByName(criteria.getName());
        }
    }

}
