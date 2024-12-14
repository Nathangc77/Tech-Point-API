package com.moreira.techpoint.dtos;

import com.moreira.techpoint.entities.Employee;

public class EmployeeMinDTO {

    private String employeeCode;
    private String name;

    public EmployeeMinDTO(String employeeCode, String name) {
        this.employeeCode = employeeCode;
        this.name = name;
    }

    public EmployeeMinDTO(Employee entity) {
        employeeCode = entity.getEmployeeCode();
        name = entity.getName();
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getName() {
        return name;
    }
}
