package com.moreira.techpoint.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "tb_time_bank")
public class TimeBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @Column(columnDefinition = "TIME")
    private LocalTime clockIn;
    @Column(columnDefinition = "TIME")
    private LocalTime lunchOut;
    @Column(columnDefinition = "TIME")
    private LocalTime lunchIn;
    @Column(columnDefinition = "TIME")
    private LocalTime clockOut;

    @ManyToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;

    private boolean isDeleted;
    private String deleteReason;

    public TimeBank() {
    }

    public TimeBank(Long id, LocalDate date, LocalTime clockIn, LocalTime lunchOut, LocalTime lunchIn, LocalTime clockOut) {
        this.id = id;
        this.date = date;
        this.clockIn = clockIn;
        this.lunchOut = lunchOut;
        this.lunchIn = lunchIn;
        this.clockOut = clockOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    public LocalTime getLunchOut() {
        return lunchOut;
    }

    public void setLunchOut(LocalTime lunchOut) {
        this.lunchOut = lunchOut;
    }

    public LocalTime getLunchIn() {
        return lunchIn;
    }

    public void setLunchIn(LocalTime lunchIn) {
        this.lunchIn = lunchIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TimeBank timeBank = (TimeBank) o;
        return Objects.equals(id, timeBank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
