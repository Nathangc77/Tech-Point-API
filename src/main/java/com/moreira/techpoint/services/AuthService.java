package com.moreira.techpoint.services;

import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.services.exceptions.ForbbidenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private EmployeeService employeeService;

    public void validateEmployeeOrAdmin(Long id) {
        Employee employee = employeeService.authenticated();
        if (!employee.hasRole("ROLE_ADMIN") && !employee.getId().equals(id))
            throw new ForbbidenException("Acesso negado");
    }
}
