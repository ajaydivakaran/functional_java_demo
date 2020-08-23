package me.spike.web.contract;

import me.spike.domain.model.Either;
import me.spike.domain.model.Employee;

public class CreateEmployeeRequest {
    private String name;
    private String departmentCode;

    public Either<Employee> toEmployee() {
        return new Employee.Builder()
                .setName(name)
                .setDepartmentCode(departmentCode)
                .tryBuild();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
