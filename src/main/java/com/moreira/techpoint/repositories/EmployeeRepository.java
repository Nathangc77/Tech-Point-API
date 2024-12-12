package com.moreira.techpoint.repositories;

import com.moreira.techpoint.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}