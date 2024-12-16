package com.moreira.techpoint.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String employeeCode;
    private String name;
    @Column(unique = true)
    private String cpf;
    private LocalDate birthDate;
    private String password;

    @OneToMany(mappedBy = "employee")
    List<TimeBank> timeBankList = new ArrayList<>();

    public Employee() {
    }

    public Employee(Long id, String employeeCode, String name, String cpf, LocalDate birthDate, String password) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TimeBank> getTimeBankList() {
        return timeBankList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @PostPersist
    private void generateCode() {
        this.employeeCode = "EMP-" + this.id;
    }
}
