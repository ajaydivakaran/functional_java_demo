package me.spike.web.contract;

import me.spike.domain.model.Error;
import me.spike.domain.model.Registration;

public class CreateEmployeeResponse {
    private Error error;
    private String employeeId;
    public CreateEmployeeResponse(Registration registration) {
        this.employeeId = registration.getEmployeeId();
    }

    public CreateEmployeeResponse(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}

