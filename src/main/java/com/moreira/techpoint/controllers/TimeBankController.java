package com.moreira.techpoint.controllers;

import com.moreira.techpoint.dtos.DeletionRequestDTO;
import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.dtos.UpdateTimeBankDTO;
import com.moreira.techpoint.services.TimeBankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/timebank")
public class TimeBankController {

    @Autowired
    private TimeBankService service;

    @Operation(
            description = "GET all timebanks",
            summary = "List all timebanks",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<TimeBankDTO>> findAll(Pageable pageable) {
        Page<TimeBankDTO> result = service.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @Operation(
            description = "GET timebank by Employee Code",
            summary = "GET timebank by Employee Code",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @GetMapping(value = "/employee")
    public ResponseEntity<Page<TimeBankDTO>> searchTimeBankByEmployee(@RequestParam(name="code") String employeeCode, Pageable pageable) {
        Page<TimeBankDTO> result = service.searchTimeBankByEmployee(employeeCode, pageable);
        return ResponseEntity.ok(result);
    }

    @Operation(
            description = "GET timebank by id",
            summary = "GET timebank by id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TimeBankDTO> findById(@PathVariable Long id) {
        TimeBankDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            description = "Create a new daily registration",
            summary = "Create a new daily registration",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @PostMapping(value = "/{employeeId}")
    public ResponseEntity<TimeBankDTO> insert(@PathVariable Long employeeId) {
        TimeBankDTO dto = service.insert(employeeId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{employeeCode}")
                .buildAndExpand(dto.getEmployeeId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(
            description = "Create a new daily registration (ADMIN)",
            summary = "Create a new daily registration (ADMIN)",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/admin")
    public ResponseEntity<TimeBankDTO> insertManual(@Valid @RequestBody TimeBankDTO dto) {
        dto = service.insertManual(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(
            description = "Update daily registration",
            summary = "Update daily registration",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TimeBankDTO> update(@PathVariable Long id, @RequestBody UpdateTimeBankDTO dto) {
        TimeBankDTO timeBankDTO = service.update(id, dto);
        return ResponseEntity.ok(timeBankDTO);
    }

    @Operation(
            description = "Update daily registration (ADMIN)",
            summary = "Update daily registration (ADMIN)",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/admin/{id}")
    public ResponseEntity<TimeBankDTO> updateManual(@PathVariable Long id, @Valid @RequestBody TimeBankDTO dto) {
        dto = service.updateManual(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            description = "Soft Delete of registration",
            summary = "Soft Delete of registration",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/delete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id, @Valid @RequestBody DeletionRequestDTO dto)  {
        service.softDelete(id, dto);
        return ResponseEntity.noContent().build();
    }
}
