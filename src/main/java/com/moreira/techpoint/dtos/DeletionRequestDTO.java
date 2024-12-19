package com.moreira.techpoint.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeletionRequestDTO {

    @Size(min = 20, message = "Insira corretamente o motivo da exclusão")
    @NotBlank(message = "Campo Requerido")
    private String deleteReason;

    public DeletionRequestDTO() {
    }

    public DeletionRequestDTO(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }
}
