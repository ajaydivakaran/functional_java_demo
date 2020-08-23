package me.spike.domain.model;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class EmployeeSearchCriteria {
    private String name;
    private String departmentCode;

    private EmployeeSearchCriteria() {
    }

    public boolean isComplexSearch() {
        return name != null && departmentCode != null;
    }

    public boolean hasName() {
        return name != null;
    }

    public boolean hasDepartmentCode() {
        return departmentCode != null;
    }

    public String getName() {
        return name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public static class Builder {
        private static final Error INVALID_CRITERIA =
                new Error("INVALID_SEARCH_CRITERIA", "Invalid search criteria");
        private EmployeeSearchCriteria criteria = new EmployeeSearchCriteria();

        public Builder setName(String name) {
            criteria.name = name;
            return this;
        }

        public Builder setDepartmentCode(String departmentCode) {
            criteria.departmentCode = departmentCode;
            return this;
        }

        public Either<EmployeeSearchCriteria> tryBuild() {
            if(isInvalid())
                return new Either<>(INVALID_CRITERIA);
            return new Either<>(criteria);
        }

        private boolean isInvalid() {
            return isEmpty(criteria.name)
                    && isEmpty(criteria.departmentCode);
        }

    }
}
