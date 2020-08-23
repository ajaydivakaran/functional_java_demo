package me.spike.web.controller;

import me.spike.domain.model.*;
import me.spike.domain.model.Error;
import me.spike.domain.service.EmployeeService;
import me.spike.web.contract.CreateEmployeeRequest;
import me.spike.web.contract.CreateEmployeeResponse;
import me.spike.web.contract.EmployeeSearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/employee")
    public ResponseEntity<CreateEmployeeResponse> create(@RequestBody CreateEmployeeRequest employeeRequest) {
        final Either<Error, Employee> maybeEmployee = employeeRequest.toEmployee();
        return maybeEmployee
                .mapRight(this::onValidEmployee)
                .mapLeft(this::onInvalidNewEmployee)
                .reduce(response -> response);
    }

    @GetMapping("/employees")
    public ResponseEntity<EmployeeSearchResponse> find(@RequestParam(required = false) String departmentCode,
                                                       @RequestParam(required = false) String name) {
        final Either<Error, EmployeeSearchCriteria> maybeSearchCriteria = toSearchCriteria(departmentCode, name);
        return maybeSearchCriteria
                .mapRight(this::onValidCriteria)
                .mapLeft(this::onInvalidCriteria)
                .reduce(response -> response);
    }

    private ResponseEntity<EmployeeSearchResponse> onInvalidCriteria(Error error) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new EmployeeSearchResponse(error));
    }

    private ResponseEntity<EmployeeSearchResponse> onValidCriteria(EmployeeSearchCriteria criteria) {
        final List<Employee> employees = service.search(criteria);
        return ResponseEntity
                .ok(new EmployeeSearchResponse(employees));
    }

    private Either<Error, EmployeeSearchCriteria> toSearchCriteria(String departmentCode,
                                                                   String name) {
        return new EmployeeSearchCriteria.Builder()
                .setDepartmentCode(departmentCode)
                .setName(name)
                .tryBuild();
    }

    private ResponseEntity<CreateEmployeeResponse> onValidEmployee(Employee employee) {
        final Either<Error, Registration> maybeRegistration = service.register(employee);
        return maybeRegistration
                .mapRight(this::onRegistrationSuccess)
                .mapLeft(this::onInvalidNewEmployee)
                .reduce(response -> response);
    }

    private ResponseEntity<CreateEmployeeResponse> onRegistrationSuccess(Registration registration) {
        final CreateEmployeeResponse response = new CreateEmployeeResponse(registration);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    private ResponseEntity<CreateEmployeeResponse> onInvalidNewEmployee(Error error) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new CreateEmployeeResponse(error));
    }
}
