package com.moreira.techpoint.controllers;

import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.services.TimeBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
