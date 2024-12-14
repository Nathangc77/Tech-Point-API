package com.moreira.techpoint.controllers;

import com.moreira.techpoint.dtos.EmployeeMinDTO;
import com.moreira.techpoint.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeMinDTO>> findAll() {
       List<EmployeeMinDTO> result = service.findAll();
       return ResponseEntity.ok(result);
    }


}
