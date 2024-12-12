package com.moreira.techpoint.dtos;

import com.moreira.techpoint.entities.TimeBank;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeBankDTO {

    private Long id;
    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime lunchOut;
    private LocalTime lunchIn;
    private LocalTime clockOut;
    private EmployeeDTO employee;

    public TimeBankDTO(Long id, LocalDate date, LocalTime clockIn, LocalTime lunchOut, LocalTime lunchIn, LocalTime clockOut, EmployeeDTO employee) {
        this.id = id;
        this.date = date;
        this.clockIn = clockIn;
        this.lunchOut = lunchOut;
        this.lunchIn = lunchIn;
        this.clockOut = clockOut;
        this.employee = employee;
    }

    public TimeBankDTO(TimeBank entity) {
        id = entity.getId();
        date = entity.getDate();
        clockIn = entity.getClockIn();
        lunchOut = entity.getLunchOut();
        lunchIn = entity.getLunchIn();
        clockOut = entity.getClockOut();
        employee = new EmployeeDTO(entity.getEmployee());
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

    public EmployeeDTO getEmployee() {
        return employee;
    }
}
