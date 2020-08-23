package me.spike.domain.model;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Employee {

    private String name;
    private String departmentCode;

    private Employee() {
    }

    public String getName() {
        return name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public static class Builder {
        private static final Error INVALID_NEW_EMPLOYEE =
                new Error("INVALID_NEW_EMPLOYEE", "Invalid new employee");
        private Employee employee = new Employee();

        public Builder setName(String name) {
            employee.name = name;
            return this;
        }

        public Builder setDepartmentCode(String departmentCode) {
            employee.departmentCode = departmentCode;
            return this;
        }

        public Either<Error, Employee> tryBuild() {
            if(invalid())
                return new Left<>(INVALID_NEW_EMPLOYEE);
            return new Right<>(employee);
        }

        private boolean invalid() {
            return isEmpty(employee.name)
                    || isEmpty(employee.departmentCode);
        }

    }

}
