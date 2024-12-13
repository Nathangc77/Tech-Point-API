package com.moreira.techpoint.dtos;

public class DeletionRequestDTO {

    private String deleteReason;

    public DeletionRequestDTO(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }
}
