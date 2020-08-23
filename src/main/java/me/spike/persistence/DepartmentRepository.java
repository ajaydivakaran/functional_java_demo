package me.spike.persistence;

import me.spike.domain.model.Department;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentRepository {

    //Fake repository

    public Optional<Department> find(String departmentCode) {
        return "IT".equals(departmentCode)
                ? Optional.of(new Department(departmentCode, "Information Technology"))
                : Optional.empty();
    }
}
