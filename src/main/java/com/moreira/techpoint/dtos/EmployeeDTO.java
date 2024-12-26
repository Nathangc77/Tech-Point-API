package com.moreira.techpoint.dtos;

import com.moreira.techpoint.entities.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EmployeeDTO {

    @Schema(description = "Database generated movie ID")
    private Long id;
    private String employeeCode;
    @Size(min = 6, max = 80, message = "Campo precisa ter de 6 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @Size(min = 11, max = 11, message = "Campo precisa ter 11 dígitos")
    @NotBlank(message = "Campo requerido")
    private String cpf;
    @NotNull(message = "Campo requerido")
    @PastOrPresent(message = "Data inválida")
    private LocalDate birthDate;

    public EmployeeDTO() {
    }

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
