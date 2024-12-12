package com.moreira.techpoint.controllers;

import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.services.TimeBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/timebank")
public class TimeBankController {

    @Autowired
    private TimeBankService service;

    @GetMapping
    public ResponseEntity<Page<TimeBankDTO>> findAll(Pageable pageable) {
        Page<TimeBankDTO> result = service.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<Page<TimeBankDTO>> searchTimeBankByEmployee(@RequestParam(name="code") String employeeCode, Pageable pageable) {
        Page<TimeBankDTO> result = service.searchTimeBankByEmployee(employeeCode, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TimeBankDTO> findById(@PathVariable Long id) {
        TimeBankDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping(value = "/{employeeId}")
    public ResponseEntity<TimeBankDTO> insert(@PathVariable Long employeeId) {
        TimeBankDTO dto = service.insert(employeeId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{employeeCode}")
                .buildAndExpand(dto.getEmployee().getEmployeeCode()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
