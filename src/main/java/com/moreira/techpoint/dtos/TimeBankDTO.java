package com.moreira.techpoint.dtos;

import com.moreira.techpoint.entities.TimeBank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeBankDTO {

    @Schema(description = "Database generated movie ID")
    private Long id;
    @NotNull(message = "Campo requerido")
    private LocalDate date;
    @NotNull(message = "Campo requerido")
    private LocalTime clockIn;
    @NotNull(message = "Campo requerido")
    private LocalTime lunchOut;
    @NotNull(message = "Campo requerido")
    private LocalTime lunchIn;
    @NotNull(message = "Campo requerido")
    private LocalTime clockOut;
    @NotNull(message = "Campo requerido")
    private Long employeeId;

    public TimeBankDTO(Long id, LocalDate date, LocalTime clockIn, LocalTime lunchOut, LocalTime lunchIn, LocalTime clockOut, Long employeeId) {
        this.id = id;
        this.date = date;
        this.clockIn = clockIn;
        this.lunchOut = lunchOut;
        this.lunchIn = lunchIn;
        this.clockOut = clockOut;
        this.employeeId = employeeId;
    }

    public TimeBankDTO() {
    }

    public TimeBankDTO(TimeBank entity) {
        id = entity.getId();
        date = entity.getDate();
        clockIn = entity.getClockIn();
        lunchOut = entity.getLunchOut();
        lunchIn = entity.getLunchIn();
        clockOut = entity.getClockOut();
        employeeId = entity.getEmployee().getId();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public LocalTime getLunchOut() {
        return lunchOut;
    }

    public LocalTime getLunchIn() {
        return lunchIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
