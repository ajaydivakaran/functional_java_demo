package me.spike.persistence;

import me.spike.domain.model.Employee;
import me.spike.domain.model.EmployeeSearchCriteria;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Service
public class EmployeeRepository {

    public String save(Employee employee) {
        final EmployeeDto employeeDto = new EmployeeDto(employee);
        //save dto
        employeeDto.setId(UUID.randomUUID().toString());
        return employeeDto.getId();
    }

    public List<Employee> find(EmployeeSearchCriteria criteria) {
        return Arrays.asList(
                createFakeEmployee("John"),
                createFakeEmployee("Jane")
        );
    }

    public List<Employee> findByDepartmentCode(String departmentCode) {
        return singletonList(createFakeEmployee("John"));
    }

    public List<Employee> findByName(String name) {
        return singletonList(createFakeEmployee("John"));
    }

    private Employee createFakeEmployee(String name) {
        final EmployeeDto dto = new EmployeeDto();
        dto.setDepartmentCode("IT");
        dto.setName(name);
        return dto.toEmployee();
    }
}

