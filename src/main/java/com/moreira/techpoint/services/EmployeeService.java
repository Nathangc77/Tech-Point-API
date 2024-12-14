package com.moreira.techpoint.services;

import com.moreira.techpoint.dtos.EmployeeDTO;
import com.moreira.techpoint.dtos.EmployeeMinDTO;
import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<EmployeeMinDTO> findAll() {
        List<Employee> result = repository.findAll();
        return result.stream().map(x -> new EmployeeMinDTO(x)).toList();
    }
    
}
