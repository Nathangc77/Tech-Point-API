package com.moreira.techpoint.dtos;

import com.moreira.techpoint.entities.Employee;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    private String employeeId;
    private String name;
    private String cpf;
    private LocalDate birthDate;

    public EmployeeDTO(Long id, String employeeId, String name, String cpf, LocalDate birthDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public EmployeeDTO(Employee entity) {
        id = entity.getId();
        employeeId = entity.getEmployeeId();
        name = entity.getName();
        cpf = entity.getCpf();
        birthDate = entity.getBirthDate();
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
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
