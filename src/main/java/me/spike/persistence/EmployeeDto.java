package me.spike.persistence;

import me.spike.domain.model.Employee;

//JPA annotations
public class EmployeeDto {

    //JPA annotations
    private String id;

    //JPA annotations
    private String name;

    //JPA annotations
    private String departmentCode;

    public EmployeeDto(Employee employee) {
        this.name = employee.getName();
        this.departmentCode = employee.getDepartmentCode();
    }

    public EmployeeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getId() {
        return id;
    }

    public Employee toEmployee() {
        return new Employee.Builder()
                .setName(name)
                .setDepartmentCode(departmentCode)
                .tryBuild()
                .getRight()
                .get();
    }

    public void setId(String id) {
        this.id = id;
    }
}
