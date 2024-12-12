package com.moreira.techpoint.dtos;

import com.moreira.techpoint.entities.Employee;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    private String employeeCode;
    private String name;
    private String cpf;
    private LocalDate birthDate;

    public EmployeeDTO(Long id, String employeeCode, String name, String cpf, LocalDate birthDate) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public EmployeeDTO(Employee entity) {
        id = entity.getId();
        employeeCode = entity.getEmployeeCode();
        name = entity.getName();
        cpf = entity.getCpf();
        birthDate = entity.getBirthDate();
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
