package com.moreira.techpoint.controllers;

import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.services.TimeBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Page<TimeBankDTO>> searchTimeBankByEmployee(@PathVariable String employeeId, Pageable pageable) {
        Page<TimeBankDTO> result = service.searchTimeBankByEmployee(employeeId, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<TimeBankDTO> insert(@PathVariable Long id) {
        TimeBankDTO dto = service.insert(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}")
                .buildAndExpand(dto.getEmployee().getEmployeeId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
