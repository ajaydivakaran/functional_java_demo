package me.spike.web.contract;

import me.spike.domain.model.Employee;
import me.spike.domain.model.Error;

import java.util.List;

public class EmployeeSearchResponse {
    private Error error;
    private List<Employee> employees;

    public EmployeeSearchResponse(Error error) {
        this.error = error;
    }

    public EmployeeSearchResponse(List<Employee> employees) {
        this.employees = employees;
    }

    public Error getError() {
        return error;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
